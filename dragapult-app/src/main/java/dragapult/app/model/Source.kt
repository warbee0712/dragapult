package dragapult.app.model

enum class Source {

    Json,
    Csv;

    companion object {

        fun valuesString() = listOf("json", "csv")

        fun valueOfOption(type: String) = when (type) {
            "json" -> Json
            "csv" -> Csv
            else -> throw IllegalArgumentException("Unsupported file type $type")
        }

    }

}