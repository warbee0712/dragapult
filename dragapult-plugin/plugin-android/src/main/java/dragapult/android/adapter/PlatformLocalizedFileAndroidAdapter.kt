package dragapult.android.adapter

import dragapult.android.LocalizationTypeAndroid
import dragapult.core.LocalizationReader.Companion.localizationReader
import dragapult.core.LocalizedPair
import dragapult.core.PlatformLocalizedFile
import java.io.File
import java.util.*

data class PlatformLocalizedFileAndroidAdapter(
    private val file: File
) : PlatformLocalizedFile() {

    override val locale: Locale
        get() = localizedDirectoryPattern.find(file.parent)?.groupValues?.getOrNull(1)
            ?.let(Locale::forLanguageTag)
            ?: Locale.forLanguageTag("en")

    override val values: Sequence<LocalizedPair>
        get() = file
            .localizationReader(LocalizationTypeAndroid).read()
            .map { (key, value) -> LocalizedPair(key, value) }

    companion object {

        private val localizedDirectoryPattern = Regex(".*-(a-z\\-_A-Z)+")

    }

}