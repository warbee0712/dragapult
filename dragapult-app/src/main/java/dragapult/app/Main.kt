package dragapult.app

import dragapult.app.options.OptionsResolverConsume
import dragapult.app.options.OptionsResolverGenerate
import dragapult.app.options.OptionsResolverGeneric
import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.DefaultParser

fun main(args: Array<out String>) {
    val options = OptionsResolverGeneric().resolve(args)
    when {
        options.help -> options.printHelp()
        options.consume -> consumeSubroutine(routineArguments(args))
        options.generate -> generateSubroutine(routineArguments(args))
    }
}

private fun routineArguments(args: Array<out String>): Array<String> {
    return args.takeLast(args.size - 1).toTypedArray()
}

private fun consumeSubroutine(args: Array<out String>) {
    val options = OptionsResolverConsume(getParser()).resolve(args)
    if (options.help) return options.printHelp()
    import(options)
}

private fun generateSubroutine(args: Array<out String>) {
    val options = OptionsResolverGenerate(getParser()).resolve(args)
    if (options.help) return options.printHelp()
    export(options)
}

private fun getParser(): CommandLineParser = DefaultParser.builder()
    .setAllowPartialMatching(false)
    .setStripLeadingAndTrailingQuotes(true)
    .build()
