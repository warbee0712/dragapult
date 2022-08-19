package dragapult.core

import dragapult.core.LocalizationKeyReader.Companion.asLocalizationKeyReader
import dragapult.core.tooling.loadServices
import java.io.File

abstract class ExternalLocalizationHolder : Model {

    abstract val key: String
    abstract val values: Sequence<TranslationHolder>

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ExternalLocalizationHolder) return false

        if (key != other.key) return false
        if (values != other.values) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + values.hashCode()
        return result
    }

    interface Factory {

        val type: LocalizationType

        fun fromFile(file: File): Sequence<ExternalLocalizationHolder> {
            val reader = file.asLocalizationKeyReader(type)
            return sequence {
                for (key in reader.keys) {
                    val values = reader.read(key)
                        .map { (locale, value) -> TranslationHolder(locale, value) }
                    val holder = ExternalLocalizationHolder(key = key, values = values)
                    yield(holder)
                }
            }
        }

        companion object {

            fun File.asHolders(type: LocalizationType): Sequence<ExternalLocalizationHolder> {
                return loadServices<Factory>().first { it.type == type }.fromFile(this)
            }

        }

    }

    companion object {

        operator fun invoke(
            key: String,
            values: Sequence<TranslationHolder>
        ): ExternalLocalizationHolder = ExternalLocalizationHolderDefault(
            key = key,
            values = values
        )

    }

}

private data class ExternalLocalizationHolderDefault(
    override val key: String,
    override val values: Sequence<TranslationHolder>
) : ExternalLocalizationHolder()
