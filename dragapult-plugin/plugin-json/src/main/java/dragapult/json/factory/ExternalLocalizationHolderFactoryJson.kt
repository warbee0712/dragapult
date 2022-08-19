package dragapult.json.factory

import com.google.auto.service.AutoService
import dragapult.core.ExternalLocalizationHolder
import dragapult.core.LocalizationType
import dragapult.json.LocalizationTypeJson

@AutoService(ExternalLocalizationHolder.Factory::class)
class ExternalLocalizationHolderFactoryJson : ExternalLocalizationHolder.Factory {

    override val type: LocalizationType
        get() = LocalizationTypeJson

}