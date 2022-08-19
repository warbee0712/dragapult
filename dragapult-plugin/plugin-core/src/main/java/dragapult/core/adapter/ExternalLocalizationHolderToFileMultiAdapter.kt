package dragapult.core.adapter

import dragapult.core.ExternalLocalizationHolder
import dragapult.core.PlatformLocalizedFile

class ExternalLocalizationHolderToFileMultiAdapter(
    private val source: Sequence<PlatformLocalizedFile>
) : Sequence<ExternalLocalizationHolder> {

    override fun iterator(): Iterator<ExternalLocalizationHolder> {
        return iterator {
            val keys = source.flatMap { it.keys }
            for (key in keys) {
                val holders = mutableSetOf<PlatformLocalizedFile>()
                for (file in source) {
                    if (key !in file.keys) continue
                    holders += file
                }
                val holder = ExternalLocalizationHolderToFileAdapter(key, holders)
                yield(holder)
            }
        }
    }

}

fun Sequence<PlatformLocalizedFile>.asExternal(): Sequence<ExternalLocalizationHolder> {
    return ExternalLocalizationHolderToFileMultiAdapter(this)
}