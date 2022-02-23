package dragapult.gradle.task

import dragapult.gradle.extension.DragapultExtension
import dragapult.gradle.extension.dragapult
import dragapult.gradle.extension.getDownloadDirDefault
import dragapult.gradle.extension.getUrlDefault
import dragapult.gradle.factory.TaskFactory
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider
import java.io.File

class ExtractTaskFactory : TaskFactory {

    override fun getInstance(project: Project): TaskProvider<out Task> {
        val extension = project.extensions.dragapult()
        return project.tasks.register("dragapultExtract", ExtractTask::class.java) {
            it.group = "i18n"
            it.dependsOn("dragapultDownload")
            it.getZipFile().set(getZipFile(extension, it.project))
        }
    }

    private fun getZipFile(extension: DragapultExtension, project: Project): File {
        val properties = extension.getArchive()
        val directory = properties.getDownloadDirDefault(project)
        val fileName = properties.getUrlDefault().map { it.substringAfterLast("/") }
        return File(directory.get().asFile, fileName.get())
    }

}