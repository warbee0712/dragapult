package dragapult.app.options

import dragapult.app.model.Platform
import dragapult.app.model.Source
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options
import java.io.File

class GenerateOptions(
    private val cli: CommandLine
) : SubroutineOptions {

    val inputFile
        get() = File(cli.getOptionValue(InputFile))

    val outputDirectory
        get() = File(cli.getOptionValue(OutputDirectory))

    val outputType
        get() = Platform.valueOfOption(cli.getOptionValue(OutputType))

    val inputType
        get() = Source.valueOfOption(cli.getOptionValue(InputType))

    val help
        get() = cli.hasOption(Help)


    override fun printHelp() =
        options().printHelp()


    companion object {

        private val InputFile = Option.builder()
            .option("i")
            .longOpt("input-file")
            .hasArg()
            .desc("Required. Input file used for generating outputs. Doesn't infer file type from extension automatically, it needs to be specified as part of \"input-type\" argument. Example: \"-i common/translations.csv\"")
            .build()

        private val OutputDirectory = Option.builder()
            .option("o")
            .longOpt("output-directory")
            .hasArg()
            .desc("Required. Output directory meant for generating platform resource. Accepts a single parameter which represents the very parent of all resource configurations. Accepts relative paths. Example: \"-o src/res\"")
            .build()

        private val OutputType = Option.builder()
            .option("t")
            .longOpt("output-type")
            .hasArg()
            .desc("Required. Defines output type for the \"output-directory\" argument. It takes in account the different project structures for respective the types. Allowed types are ${Platform.valuesString()}.")
            .build()

        private val InputType = Option.builder()
            .option("s")
            .longOpt("input-type")
            .hasArg()
            .desc("Required. Defines input type for \"input-file\" argument. It chooses a specific parser capable of converting the data. Allowed types are ${Source.valuesString()}")
            .build()

        private val Help = Option.builder()
            .option("h")
            .longOpt("help")
            .desc("Prints this help message")
            .build()

        fun options(): Options = Options()
            .addOption(InputFile)
            .addOption(OutputDirectory)
            .addOption(OutputType)
            .addOption(InputType)
            .addOption(Help)

    }

}