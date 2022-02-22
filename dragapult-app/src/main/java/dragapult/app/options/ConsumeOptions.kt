package dragapult.app.options

import dragapult.app.model.Platform
import dragapult.app.model.Source
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options
import java.io.File

class ConsumeOptions(
    private val cli: CommandLine
) : SubroutineOptions {

    val inputDirectory
        get() = File(cli.getOptionValue(InputDirectory).let(::requireNotNull))

    val outputFile
        get() = File(cli.getOptionValue(OutputFile).let(::requireNotNull))

    val inputType
        get() = Platform.valueOfOption(cli.getOptionValue(InputType).let(::requireNotNull))

    val outputType
        get() = Source.valueOfOption(cli.getOptionValue(OutputType).let(::requireNotNull))

    val help
        get() = cli.hasOption(Help)


    override fun printHelp() =
        options().printHelp()


    companion object {

        private val InputDirectory = Option.builder()
            .option("i")
            .longOpt("input-directory")
            .hasArg()
            .desc("Required. Input directory meant for resource consumption. Accepts a single parameter which represents the nearest parent of all resource configurations. Accepts relative paths. Example: \"-i src/res\"")
            .build()

        private val OutputFile = Option.builder()
            .option("o")
            .longOpt("output-file")
            .hasArg()
            .desc("Required. Output file created after consuming all resource input files. It will be created exactly as specified. May or may not require you to create parent folders. Doesn't infer file type from extension automatically, it needs to be specified as part of \"output-type\" argument. Example: \"-o out/translations.csv\"")
            .build()

        private val InputType = Option.builder()
            .option("t")
            .longOpt("input-type")
            .hasArg()
            .desc("Required. Defines input type for the \"input-directory\" argument. It takes in account the different project structures for respective the types. Allowed types are ${Platform.valuesString()}.")
            .build()

        private val OutputType = Option.builder()
            .option("r")
            .longOpt("output-type")
            .hasArg()
            .desc("Required. Defines output type for \"output-file\" argument. It chooses a specific parser capable of converting the data. Allowed types are ${Source.valuesString()}")
            .build()

        private val Help = Option.builder()
            .option("h")
            .longOpt("help")
            .desc("Prints this help message")
            .build()

        fun options(): Options = Options()
            .addOption(InputDirectory)
            .addOption(OutputFile)
            .addOption(InputType)
            .addOption(OutputType)
            .addOption(Help)

    }

}