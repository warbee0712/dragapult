package dragapult.app.options

import org.apache.commons.cli.Options

interface OptionsResolver<Options> {

    fun resolve(arguments: Array<out String>): Options

}