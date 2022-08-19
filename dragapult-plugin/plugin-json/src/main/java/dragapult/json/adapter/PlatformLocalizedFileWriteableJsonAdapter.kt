package dragapult.json.adapter

import dragapult.core.LocalizationType
import dragapult.core.PlatformLocalizedFile
import dragapult.core.adapter.AbstractPlatformLocalizedFileWriteableAdapter
import dragapult.json.LocalizationTypeJson

class PlatformLocalizedFileWriteableJsonAdapter(
    origin: PlatformLocalizedFile
) : AbstractPlatformLocalizedFileWriteableAdapter(origin) {

    override val type: LocalizationType
        get() = LocalizationTypeJson

    override val fileName: String
        get() = "${locale.language}.json"

}