package dragapult.apple

import dragapult.core.LocalizationType

object LocalizationTypeApple : LocalizationType {

    override val mimeType: String = "text/strings"

    override fun equals(other: Any?): Boolean {
        return other is LocalizationTypeApple
    }

    override fun hashCode(): Int {
        return mimeType.hashCode()
    }

    override fun toString(): String {
        return "Apple(mimeType=$mimeType)"
    }

}