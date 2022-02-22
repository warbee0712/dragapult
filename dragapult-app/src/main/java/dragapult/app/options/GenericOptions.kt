package dragapult.app.options

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options

class GenericOptions(
    private val cli: CommandLine
) : SubroutineOptions {

    val consume
        get() = cli.hasOption(Consume)

    val generate
        get() = cli.hasOption(Generate)

    val help
        get() = cli.hasOption(Help)


    override fun printHelp() =
        options().printHelp()


    companion object {

        private val Consume = Option.builder()
            .argName("consume")
            .option("c")
            .desc("Consumes platform files into a common file type. Try \"consume --help\" for usage info.")
            .build()

        private val Generate = Option.builder()
            .argName("generate")
            .option("g")
            .desc("Generates platform files from a common file type. Try \"generate --help\" for usage info.")
            .build()

        private val Help = Option.builder()
            .option("h")
            .longOpt("help")
            .desc("Prints this help message.")
            .build()

        fun options(): Options = Options()
            .addOption(Consume)
            .addOption(Generate)
            .addOption(Help)

    }

}