package dragapult.csv.adapter

import dragapult.core.AbstractExternalLocalizationHolder
import dragapult.core.LocalizationType
import dragapult.csv.LocalizationTypeCsv
import java.io.File

class ExternalLocalizationHolderCsv(
    override val file: File,
    override val key: String
) : AbstractExternalLocalizationHolder() {

    override val type: LocalizationType
        get() = LocalizationTypeCsv

}