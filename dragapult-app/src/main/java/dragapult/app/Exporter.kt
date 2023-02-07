package dragapult.app

import dragapult.app.options.GenerateOptions
import dragapult.core.ExternalLocalizationHolder.Factory.Companion.asHolders
import dragapult.core.PlatformLocalizedFileWriteable.Factory.Companion.asWriteable
import dragapult.core.PlatformLocalizedFileWriteable.Factory.Companion.asWriteableWithBlank
import dragapult.core.adapter.asPlatform

fun export(options: GenerateOptions) {
    options.inputFile
        .asHolders(options.inputType.type)
        .asPlatform()
        .map {
            when (options.hasBlankValues) {
                true -> it.asWriteableWithBlank(options.outputType.type)
                else -> it.asWriteable(options.outputType.type)
            }
        }
        .forEach { it.write(options.outputDirectory) }
}
