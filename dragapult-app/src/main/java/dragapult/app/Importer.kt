package dragapult.app

import dragapult.app.options.ConsumeOptions
import dragapult.core.ExternalLocalizationHolderWriteable.Factory.Companion.asWriteable
import dragapult.core.PlatformLocalizedFile
import dragapult.core.adapter.asExternal

fun import(options: ConsumeOptions) {
    PlatformLocalizedFile.Factory.fromDirectory(options.inputDirectory, options.inputType.type)
        .asExternal()
        .asWriteable(options.outputType.type)
        .write(options.outputFile)
}
