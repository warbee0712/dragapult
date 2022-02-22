package dragapult.app.options

import org.apache.commons.cli.CommandLineParser

class OptionsResolverConsume(
    private val parser: CommandLineParser
) : OptionsResolver<ConsumeOptions> {

    override fun resolve(arguments: Array<out String>): ConsumeOptions {
        val cli = parser.parse(ConsumeOptions.options(), arguments)
        return ConsumeOptions(cli)
    }

}