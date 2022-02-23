package dragapult.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecSpec
import java.io.File

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