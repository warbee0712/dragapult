package dragapult.app.options

import org.apache.commons.cli.CommandLineParser

class OptionsResolverGenerate(
    private val parser: CommandLineParser
) : OptionsResolver<GenerateOptions> {

    override fun resolve(arguments: Array<out String>): GenerateOptions {
        val cli = parser.parse(GenerateOptions.options(), arguments)
        return GenerateOptions(cli)
    }

}