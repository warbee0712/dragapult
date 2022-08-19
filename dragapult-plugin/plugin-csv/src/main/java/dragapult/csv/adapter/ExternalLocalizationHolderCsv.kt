package dragapult.csv.adapter

import dragapult.core.ExternalLocalizationHolder
import dragapult.core.LocalizationKeyReader.Companion.asLocalizationKeyReader
import dragapult.core.TranslationHolder
import dragapult.csv.LocalizationTypeCsv
import java.io.File

class ExternalLocalizationHolderCsv(
    private val file: File,
    override val key: String
) : ExternalLocalizationHolder() {

    override val values: Sequence<TranslationHolder>
        get() = file
            .asLocalizationKeyReader(LocalizationTypeCsv).read(key)
            .map { (locale, value) -> TranslationHolder(locale, value) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ExternalLocalizationHolderCsv) return false
        if (!super.equals(other)) return false

        if (file != other.file) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + file.hashCode()
        return result
    }

}