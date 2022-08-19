package dragapult.json.adapter

import dragapult.core.AbstractExternalLocalizationHolder
import dragapult.core.LocalizationType
import dragapult.json.LocalizationTypeJson
import java.io.File

class ExternalLocalizationHolderJson(
    override val file: File,
    override val key: String
) : AbstractExternalLocalizationHolder() {

    override val type: LocalizationType
        get() = LocalizationTypeJson

}
