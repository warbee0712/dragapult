package dragapult.apple.adapter

import dragapult.apple.LocalizationTypeApple
import dragapult.core.LocalizationType
import dragapult.core.adapter.AbstractPlatformLocalizedFileAdapter
import java.io.File
import java.util.*

data class PlatformLocalizedFileAppleAdapter(
    override val file: File
) : AbstractPlatformLocalizedFileAdapter() {

    override val locale: Locale
        get() = localizedDirectoryPattern.find(file.parent)?.groupValues?.getOrNull(1)
            ?.let(Locale::forLanguageTag)
            ?: Locale.forLanguageTag("en")

    override val type: LocalizationType
        get() = LocalizationTypeApple

    companion object {

        private val localizedDirectoryPattern = Regex("(a-z\\-_A-Z)+\\.lproj")

    }

}