package dragapult.gradle.task

import de.undercouch.gradle.tasks.download.Download
import dragapult.gradle.extension.dragapult
import dragapult.gradle.extension.getDownloadDirDefault
import dragapult.gradle.extension.getUrlDefault
import dragapult.gradle.factory.TaskFactory
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

class DownloadBinaryTaskFactory : TaskFactory {

    override fun getInstance(project: Project): TaskProvider<out Task> {
        val extension = project.extensions.dragapult()
        return project.tasks.register("dragapultDownload", Download::class.java) {
            it.group = "i18n"
            val properties = extension.getArchive()
            it.src(properties.getUrlDefault())
            it.dest(properties.getDownloadDirDefault(project))
        }
    }

}