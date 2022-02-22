package dragapult.app.model

enum class Platform {

    Android,
    Apple,
    Json;

    companion object {

        fun valuesString() = listOf("apple", "android", "json")

        fun valueOfOption(type: String) = when (type) {
            "apple" -> Apple
            "android" -> Android
            "json" -> Json
            else -> throw IllegalArgumentException("Unsupported file type $type")
        }

    }

}

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