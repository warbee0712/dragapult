package dragapult.apple.adapter

import dragapult.apple.LocalizationTypeApple
import dragapult.core.LocalizationType
import dragapult.core.PlatformLocalizedFile
import dragapult.core.adapter.AbstractPlatformLocalizedFileWriteableAdapter

class PlatformLocalizedFileWriteableAppleAdapter(
    origin: PlatformLocalizedFile,
    override val allowBlankValues: Boolean
) : AbstractPlatformLocalizedFileWriteableAdapter(origin) {

    private val dirName
        get() = "${locale.language}.lproj"

    override val type: LocalizationType
        get() = LocalizationTypeApple

    override val fileName: String
        get() = "$dirName/Localizable.strings"

}