package dragapult.core.adapter

import dragapult.core.ExternalLocalizationHolder
import dragapult.core.PlatformLocalizedFile

class PlatformLocalizedFileToHolderMultiAdapter(
    private val source: Sequence<ExternalLocalizationHolder>
) : Sequence<PlatformLocalizedFile> {

    override fun iterator(): Iterator<PlatformLocalizedFile> = iterator {
        val locales = source.flatMap { it.values }.map { it.locale }.distinct()
        for (locale in locales) {
            val holders = mutableSetOf<ExternalLocalizationHolder>()
            for (holder in source) {
                val hasLanguage = holder.values.any { it.locale == locale }
                if (!hasLanguage) continue
                holders += holder
            }
            val platformFile = PlatformLocalizedFileToHolderAdapter(locale, holders)
            yield(platformFile)
        }
    }

}

fun Sequence<ExternalLocalizationHolder>.asPlatform(): Sequence<PlatformLocalizedFile> {
    return PlatformLocalizedFileToHolderMultiAdapter(this)
}