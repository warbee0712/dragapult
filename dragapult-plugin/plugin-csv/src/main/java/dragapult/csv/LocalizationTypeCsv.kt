package dragapult.csv

import dragapult.core.LocalizationType

object LocalizationTypeCsv : LocalizationType {

    override val mimeType: String = "text/csv"

    override fun equals(other: Any?): Boolean {
        return other is LocalizationTypeCsv
    }

    override fun hashCode(): Int {
        return mimeType.hashCode()
    }

}