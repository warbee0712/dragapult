package dragapult.gradle.task

import dragapult.gradle.extension.DragapultExtension
import dragapult.gradle.extension.dragapult
import dragapult.gradle.factory.TaskFactory
import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.TaskProvider
import java.io.File

class GeneratorTaskFactory : TaskFactory {

    override fun getInstance(project: Project): TaskProvider<out Task> {
        return project.tasks.register("dragapultGenerate", GeneratorTask::class.java) {
            val extension = it.project.extensions.dragapult()
            it.group = "i18n"
            it.dependsOn("dragapultExtract")
            it.getBinary().set(getBinaryFile(it.project))
            it.getFile().set(getFile(extension))
            it.getType().set(getType(extension))
            it.getOutputDir().set(getResourcesDir(extension))
            it.project.tasks.getByName("preBuild").dependsOn(it)
        }
    }

    private fun getResourcesDir(extension: DragapultExtension): File {
        return extension.getApp().getOutput().get().asFile
    }

    private fun getFile(extension: DragapultExtension): RegularFileProperty {
        return extension.getApp().getFile()
    }

    private fun getType(extension: DragapultExtension): Property<String> {
        return extension.getApp().getType()
    }

    private fun getBinaryFile(project: Project): File {
        return File(project.buildDir, "dragapult/bin/${getLauncher()}")
    }

    private fun getLauncher(): String = when {
        Os.isFamily(Os.FAMILY_WINDOWS) -> "dragapult-app.bat"
        Os.isFamily(Os.FAMILY_UNIX) -> "dragapult-app"
        Os.isFamily(Os.FAMILY_MAC) -> "dragapult-app"
        else -> throw IllegalStateException("OS not supported")
    }

}
