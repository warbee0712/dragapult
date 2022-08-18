package dragapult.core.adapter

import dragapult.core.ExternalLocalizationHolder
import dragapult.core.LocalizedPair
import dragapult.core.PlatformLocalizedFile
import java.util.*

class PlatformLocalizedFileToHolderAdapter(
    override val locale: Locale,
    private val holders: Iterable<ExternalLocalizationHolder>
) : PlatformLocalizedFile() {

    constructor(
        locale: Locale,
        vararg holders: ExternalLocalizationHolder
    ) : this(
        locale = locale,
        holders = holders.toList()
    )

    override val values: Sequence<LocalizedPair>
        get() = sequence {
            for (holder in holders) {
                val key = holder.key
                val values = holder.values
                val value = values.firstOrNull { it.locale == locale }?.value.orEmpty()
                val pair = LocalizedPair(
                    key = key,
                    value = value
                )
                yield(pair)
            }
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlatformLocalizedFileToHolderAdapter

        if (locale != other.locale) return false
        if (holders != other.holders) return false

        return true
    }

    @Suppress("KotlinConstantConditions")
    override fun hashCode(): Int {
        var result = 0
        result = 31 * result + locale.hashCode()
        result = 31 * result + holders.hashCode()
        return result
    }

}