package dragapult.json

import dragapult.core.LocalizationType

object LocalizationTypeJson : LocalizationType {

    override val mimeType: String
        get() = "application/json"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LocalizationTypeJson) return false

        if (mimeType != other.mimeType) return false

        return true
    }

    override fun hashCode(): Int {
        return mimeType.hashCode()
    }

}