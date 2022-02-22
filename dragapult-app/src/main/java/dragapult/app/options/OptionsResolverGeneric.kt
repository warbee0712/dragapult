package dragapult.app.options

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option

class OptionsResolverGeneric : OptionsResolver<GenericOptions> {

    override fun resolve(arguments: Array<out String>): GenericOptions {
        val cliBuilder = CommandLine.Builder()
        for (option in GenericOptions.options().options)
            if (option.matches(arguments[0])) {
                cliBuilder.addOption(option)
                cliBuilder.addArg(arguments[0])
                break
            }
        val cli = cliBuilder.build()
        return GenericOptions(cli)
    }

    private fun Option.matches(arg: String): Boolean {
        return argName == arg || arg.contains(opt) || opt == "-$arg" || longOpt == "--$arg"
    }

}