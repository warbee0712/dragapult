package dragapult.json

import com.google.auto.service.AutoService
import dragapult.core.LocalizationKeyReader
import dragapult.core.LocalizationType
import dragapult.json.tooling.jsonObject
import java.io.File
import java.util.*

class LocalizationKeyReaderJson(
    private val file: File
) : LocalizationKeyReader {

    override val keys: Sequence<String>
        get() = sequence {
            yieldAll(file.jsonObject().keys())
        }

    override fun read(key: String): Sequence<Pair<Locale, String>> {
        return sequence {
            val translation = file.jsonObject().getJSONObject(key)
            for (language in translation.keys()) {
                val locale = Locale.forLanguageTag(language)
                val value = translation.getString(language)
                yield(locale to value)
            }
        }
    }

    // ---

    @AutoService(LocalizationKeyReader.Factory::class)
    class Factory : LocalizationKeyReader.Factory {

        override val type: LocalizationType
            get() = LocalizationTypeJson

        override fun create(file: File): LocalizationKeyReader {
            return LocalizationKeyReaderJson(file)
        }

    }

}