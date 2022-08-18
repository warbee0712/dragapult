package dragapult.core.adapter

import dragapult.core.ExternalLocalizationHolder
import dragapult.core.PlatformLocalizedFile
import dragapult.core.TranslationHolder

class ExternalLocalizationHolderToFileAdapter(
    override val key: String,
    private val files: Iterable<PlatformLocalizedFile>
) : ExternalLocalizationHolder() {

    constructor(
        key: String,
        vararg files: PlatformLocalizedFile
    ) : this(
        key = key,
        files = files.toList()
    )

    override val values: Sequence<TranslationHolder>
        get() = sequence {
            for (file in files) {
                val locale = file.locale
                for (pair in file.values) {
                    if (pair.key != key) continue
                    val value = pair.value
                    val holder = TranslationHolder(locale = locale, value = value)
                    yield(holder)
                }
            }
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExternalLocalizationHolderToFileAdapter

        if (key != other.key) return false
        if (files != other.files) return false

        return true
    }

    @Suppress("KotlinConstantConditions")
    override fun hashCode(): Int {
        var result = 0
        result = 31 * result + key.hashCode()
        result = 31 * result + files.hashCode()
        return result
    }

}