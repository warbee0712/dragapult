package dragapult.csv.adapter

import dragapult.core.AbstractExternalLocalizationHolderWriteable
import dragapult.core.ExternalLocalizationHolder
import dragapult.core.LocalizationType
import dragapult.csv.LocalizationTypeCsv

class ExternalLocalizationHolderWriteableCsv(
    override val source: Sequence<ExternalLocalizationHolder>
) : AbstractExternalLocalizationHolderWriteable() {

    override val type: LocalizationType
        get() = LocalizationTypeCsv

}
