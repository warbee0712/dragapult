package dragapult.gradle.task

import dragapult.gradle.extension.dragapultExtension
import dragapult.gradle.extension.getDownloadDirDefault
import dragapult.gradle.extension.getUrlDefault
import dragapult.gradle.factory.TaskFactory
import dragapult.gradle.model.TaskRegistry
import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskProvider
import java.io.File

abstract class ExtractTask : DefaultTask() {

    init {
        outputs.upToDateWhen {
            false //todo add a condition to validate the task
        }
    }

    @InputFile
    abstract fun getZipFile(): RegularFileProperty

    @TaskAction
    fun extractBinaries() {
        val file = getZipFile().get()
        val fileNameWithoutExtension = file.asFile.nameWithoutExtension
        val innerFolderPattern = Regex("${fileNameWithoutExtension}-.*")
        project.copy {
            it.from(project.zipTree(file.asFile.absolutePath))
            it.into(project.buildDir)
        }
        val src = project.buildDir.listFiles().orEmpty().firstOrNull { innerFolderPattern.matches(it.name) } ?: return
        val dst = File(project.buildDir, "dragapult")
        dst.deleteRecursively()
        src.renameTo(dst)
    }

}

class ExtractTaskFactory : TaskFactory {

    override fun getInstance(registry: TaskRegistry): TaskProvider<out Task> {
        val extension = registry.project.extensions.dragapultExtension()
        return registry.project.tasks.register("dragapultExtract", ExtractTask::class.java) {
            it.group = "i18n"
            it.dependsOn("dragapultDownload")
            val properties = extension.getArchive()
            val directory = properties.getDownloadDirDefault(it.project)
            val fileName = properties.getUrlDefault().map { it.substringAfterLast("/") }
            val file = File(directory.get().asFile, fileName.get())
            val zipFile = it.project.objects.fileProperty()
            zipFile.set(file)
            it.getZipFile().set(zipFile)
        }
    }

}