package dragapult.android.adapter

import dragapult.android.LocalizationTypeAndroid
import dragapult.core.LocalizationType
import dragapult.core.PlatformLocalizedFile
import dragapult.core.adapter.AbstractPlatformLocalizedFileWriteableAdapter

class PlatformLocalizedFileWriteableAndroidAdapter(
    origin: PlatformLocalizedFile
) : AbstractPlatformLocalizedFileWriteableAdapter(origin) {

    private val dirName
        get() = when (val l = locale.language) {
            "en" -> "values"
            else -> "values-$l"
        }

    override val type: LocalizationType
        get() = LocalizationTypeAndroid
    override val fileName: String
        get() = "$dirName/strings.xml"

}