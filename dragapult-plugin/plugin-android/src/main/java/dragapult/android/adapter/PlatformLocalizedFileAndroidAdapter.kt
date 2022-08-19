package dragapult.android.adapter

import dragapult.android.LocalizationTypeAndroid
import dragapult.core.LocalizationType
import dragapult.core.adapter.AbstractPlatformLocalizedFileAdapter
import java.io.File
import java.util.*

data class PlatformLocalizedFileAndroidAdapter(
    override val file: File
) : AbstractPlatformLocalizedFileAdapter() {

    override val locale: Locale
        get() = localizedDirectoryPattern.find(file.parent)?.groupValues?.getOrNull(1)
            ?.let(Locale::forLanguageTag)
            ?: Locale.forLanguageTag("en")

    override val type: LocalizationType
        get() = LocalizationTypeAndroid

    companion object {

        private val localizedDirectoryPattern = Regex(".*-(a-z\\-_A-Z)+")

    }

}