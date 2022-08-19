package dragapult.core

import dragapult.core.LocalizationKeyWriter.Companion.asLocalizationKeyWriter
import java.io.File

abstract class AbstractExternalLocalizationHolderWriteable : ExternalLocalizationHolderWriteable() {

    protected abstract val type: LocalizationType

    override fun write(file: File) {
        file.asLocalizationKeyWriter(type).use { writer ->
            for (holder in source) {
                for (translation in holder.values) {
                    writer.write(holder.key, translation.locale, translation.value)
                }
            }
        }
    }

}