package dragapult.gradle.task

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import dragapult.gradle.callback.OnTaskCreated
import dragapult.gradle.extension.dragapultExtension
import dragapult.gradle.factory.TaskFactory
import dragapult.gradle.factory.TaskFactoryAsync
import dragapult.gradle.log.error
import dragapult.gradle.model.TaskRegistry
import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import org.gradle.process.ExecSpec
import java.io.File
import com.android.build.gradle.internal.plugins.AppPlugin as AppPluginInternal
import com.android.build.gradle.internal.plugins.LibraryPlugin as LibraryPluginInternal

abstract class GeneratorTask : DefaultTask() {

    @InputFile
    abstract fun getFile(): RegularFileProperty

    @Input
    abstract fun getType(): Property<String>

    @InputFile
    abstract fun getBinary(): RegularFileProperty

    @OutputDirectory
    abstract fun getOutputDir(): DirectoryProperty

    @TaskAction
    fun generateResourceFiles() {
        val result = project.exec {
            it.dragapult(
                inputFile = getFile().get().asFile,
                outputDirectory = getOutputDir().get().asFile,
                outputType = "android",
                inputType = getType().get()
            )
        }
        result.assertNormalExitValue()
        result.rethrowFailure()
    }

    private fun ExecSpec.dragapult(
        inputFile: File,
        outputDirectory: File,
        outputType: String,
        inputType: String
    ) = commandLine(
        getBinary().get().asFile.absolutePath,
        "generate",
        "-i", inputFile.absolutePath,
        "-o", outputDirectory.absolutePath,
        "-t", outputType,
        "-s", inputType
    )

}

class GeneratorTaskFactoryAsync(
    private val factory: TaskFactory
) : TaskFactoryAsync {

    override fun getInstanceAsync(registry: TaskRegistry, callback: OnTaskCreated) {
        if (
            registry.project.plugins.hasPlugin(AppPlugin::class.java) ||
            registry.project.plugins.hasPlugin(LibraryPlugin::class.java)
        ) {
            return callback.onCreated(factory.getInstance(registry))
        }
        registry.project.plugins
            .matching { it is AppPlugin || it is LibraryPlugin }
            .whenPluginAdded { callback.onCreated(factory.getInstance(registry)) }
    }

}

class GeneratorTaskFactory : TaskFactory {

    override fun getInstance(registry: TaskRegistry): TaskProvider<out Task> {
        val resourcesDir = File(registry.project.buildDir, "generated/res/dragapult")
        return registry.project.tasks.register("dragapultGenerate", GeneratorTask::class.java) {
            it.group = "i18n"
            it.dependsOn("dragapultExtract")
            val extension = it.project.extensions.dragapultExtension()
            val app = extension.getApp()
            it.getBinary().set(getBinaryFile(it.project))
            it.getFile().set(app.getFile())
            it.getType().set(app.getType())
            it.getOutputDir().set(resourcesDir)
        }.also {
            registerSourceSet(registry.project, resourcesDir)
            registerTaskDependency(registry.project, it)
        }
    }

    private fun getBinaryFile(project: Project): RegularFile {
        val directory = File(project.buildDir, "dragapult")
        val bin = File(directory, "bin")
        val launcher = when {
            Os.isFamily(Os.FAMILY_WINDOWS) -> "dragapult-app.bat"
            Os.isFamily(Os.FAMILY_UNIX) -> "dragapult-app"
            Os.isFamily(Os.FAMILY_MAC) -> "dragapult-app"
            else -> throw IllegalStateException("OS not supported")
        }
        val file = project.objects.fileProperty()
        file.set(File(bin, launcher))
        return file.get()
    }

    private fun registerSourceSet(project: Project, resourcesDir: File) {
        val plugin = project.plugins.findPlugin(AppPluginInternal::class.java)
            ?: project.plugins.findPlugin(LibraryPluginInternal::class.java)
        if (plugin == null) {
            project.logger.error { "Cannot find any Android BasePlugin attached, therefore no sources or automatic build hooks are attached." }
            return
        }
        plugin.extension.sourceSets.getByName("main") {
            it.res.setSrcDirs(it.res.srcDirs + resourcesDir.absolutePath)
        }
        project.logger.error { "Attached resource dir \"$resourcesDir\" to main source set" }
    }

    private fun registerTaskDependency(project: Project, it: TaskProvider<GeneratorTask>) {
        project.tasks.getByName("preBuild").dependsOn(it)
    }

}