package dragapult.csv.adapter

import dragapult.core.ExternalLocalizationHolder
import dragapult.core.ExternalLocalizationHolderWriteable
import dragapult.core.LocalizationKeyWriter.Companion.asLocalizationKeyWriter
import dragapult.csv.LocalizationTypeCsv
import java.io.File

class ExternalLocalizationHolderWriteableCsv(
    override val source: Sequence<ExternalLocalizationHolder>
) : ExternalLocalizationHolderWriteable() {

    override fun write(file: File) {
        file.asLocalizationKeyWriter(LocalizationTypeCsv).use { writer ->
            for (holder in source) {
                for (translation in holder.values) {
                    writer.write(holder.key, translation.locale, translation.value)
                }
            }
        }
    }

}
