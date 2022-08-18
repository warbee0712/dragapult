package dragapult.android

import dragapult.core.LocalizationType

object LocalizationTypeAndroid : LocalizationType {

    override val mimeType: String = "application/xml"

    override fun equals(other: Any?): Boolean {
        return other is LocalizationTypeAndroid
    }

    override fun hashCode(): Int {
        return mimeType.hashCode()
    }

    override fun toString(): String {
        return "Android(mimeType=$mimeType)"
    }

}