package dragapult.json.adapter

import dragapult.core.LocalizationType
import dragapult.core.adapter.AbstractPlatformLocalizedFileAdapter
import dragapult.json.LocalizationTypeJson
import java.io.File
import java.util.*

class PlatformLocalizedFileJsonAdapter(
    override val file: File
) : AbstractPlatformLocalizedFileAdapter() {

    override val locale: Locale
        get() = Locale.forLanguageTag(file.nameWithoutExtension)

    override val type: LocalizationType
        get() = LocalizationTypeJson

}