package dragapult.app.options

import org.apache.commons.cli.Options

interface SubroutineOptions {

    fun printHelp()

}

fun Options.printHelp() {
    for (option in options) buildString {
        when {
            option.argName != null -> append(option.argName)
            else -> {
                append('-')
                append(option.opt)
            }
        }

        if (option.hasLongOpt()) {
            append('\t')
            append("--")
            appendLine(option.longOpt)
        } else {
            appendLine()
        }

        append('\t')
        appendLine(option.description)
    }.apply(::println)
}