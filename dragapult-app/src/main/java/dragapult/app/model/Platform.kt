package dragapult.app.model

import dragapult.android.LocalizationTypeAndroid
import dragapult.apple.LocalizationTypeApple
import dragapult.core.LocalizationType
import dragapult.json.LocalizationTypeJson

enum class Platform(val type: LocalizationType) {

    Android(LocalizationTypeAndroid),
    Apple(LocalizationTypeApple),
    Json(LocalizationTypeJson);

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