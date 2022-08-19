package dragapult.core

import dragapult.core.LocalizationKeyReader.Companion.asLocalizationKeyReader
import java.io.File

abstract class AbstractExternalLocalizationHolder : ExternalLocalizationHolder() {

    protected abstract val file: File
    protected abstract val type: LocalizationType

    override val values: Sequence<TranslationHolder>
        get() = file
            .asLocalizationKeyReader(type).read(key)
            .map { (locale, value) -> TranslationHolder(locale, value) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractExternalLocalizationHolder) return false
        if (!super.equals(other)) return false

        if (file != other.file) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + file.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }

}