package dragapult.app.model

import dragapult.core.LocalizationType
import dragapult.csv.LocalizationTypeCsv
import dragapult.json.LocalizationTypeJson

enum class Source(val type: LocalizationType) {

    Json(LocalizationTypeJson),
    Csv(LocalizationTypeCsv);

    companion object {

        fun valuesString() = listOf("json", "csv")

        fun valueOfOption(type: String) = when (type) {
            "json" -> Json
            "csv" -> Csv
            else -> throw IllegalArgumentException("Unsupported file type $type")
        }

    }

}