package dragapult.csv.factory

import com.google.auto.service.AutoService
import dragapult.core.ExternalLocalizationHolder
import dragapult.core.LocalizationType
import dragapult.csv.LocalizationTypeCsv

@AutoService(ExternalLocalizationHolder.Factory::class)
class ExternalLocalizationHolderFactoryCsv : ExternalLocalizationHolder.Factory {

    override val type: LocalizationType
        get() = LocalizationTypeCsv

}