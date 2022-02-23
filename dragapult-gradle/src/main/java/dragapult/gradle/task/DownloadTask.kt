package dragapult.gradle.task

import de.undercouch.gradle.tasks.download.Download
import dragapult.gradle.extension.dragapultExtension
import dragapult.gradle.extension.getDownloadDirDefault
import dragapult.gradle.extension.getUrlDefault
import dragapult.gradle.factory.TaskFactory
import dragapult.gradle.model.TaskRegistry
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

class DownloadBinaryTaskFactory : TaskFactory {

    override fun getInstance(registry: TaskRegistry): TaskProvider<out Task> {
        val project = registry.project
        val extension = project.extensions.dragapultExtension()
        return project.tasks.register("dragapultDownload", Download::class.java) {
            it.group = "i18n"
            val properties = extension.getArchive()
            it.src(properties.getUrlDefault())
            it.dest(properties.getDownloadDirDefault(project))
        }
    }

}