package dragapult.json.adapter

import dragapult.core.AbstractExternalLocalizationHolderWriteable
import dragapult.core.ExternalLocalizationHolder
import dragapult.core.LocalizationType
import dragapult.json.LocalizationTypeJson

class ExternalLocalizationHolderWriteableJson(
    override val source: Sequence<ExternalLocalizationHolder>
) : AbstractExternalLocalizationHolderWriteable() {

    override val type: LocalizationType
        get() = LocalizationTypeJson

}