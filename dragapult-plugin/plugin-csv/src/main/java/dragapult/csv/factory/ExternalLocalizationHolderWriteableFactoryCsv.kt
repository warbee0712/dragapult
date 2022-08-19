package dragapult.csv.factory

import com.google.auto.service.AutoService
import dragapult.core.ExternalLocalizationHolder
import dragapult.core.ExternalLocalizationHolderWriteable
import dragapult.core.LocalizationType
import dragapult.csv.LocalizationTypeCsv
import dragapult.csv.adapter.ExternalLocalizationHolderWriteableCsv

@AutoService(ExternalLocalizationHolderWriteable.Factory::class)
class ExternalLocalizationHolderWriteableFactoryCsv : ExternalLocalizationHolderWriteable.Factory {

    override val type: LocalizationType
        get() = LocalizationTypeCsv

    override fun writeable(holders: Sequence<ExternalLocalizationHolder>): ExternalLocalizationHolderWriteable {
        return ExternalLocalizationHolderWriteableCsv(holders)
    }

}