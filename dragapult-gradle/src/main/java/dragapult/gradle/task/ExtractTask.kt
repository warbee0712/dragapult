package dragapult.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class ExtractTask : DefaultTask() {

    @InputFile
    abstract fun getZipFile(): RegularFileProperty

    @TaskAction
    fun extractBinaries() {
        val file = getZipFile().get().asFile
        extractZip(file)
        renameFolder(file)
    }

    private fun extractZip(file: File) {
        project.copy {
            it.from(project.zipTree(file.absolutePath))
            it.into(project.buildDir)
        }
    }

    private fun renameFolder(file: File) {
        val fileNameWithoutExtension = file.nameWithoutExtension
        val innerFolderPattern = Regex("${fileNameWithoutExtension}-.*")
        val src = project.buildDir.listFiles().orEmpty().firstOrNull { innerFolderPattern.matches(it.name) } ?: return
        val dst = File(project.buildDir, "dragapult")
        dst.deleteRecursively()
        src.renameTo(dst)
    }

}
