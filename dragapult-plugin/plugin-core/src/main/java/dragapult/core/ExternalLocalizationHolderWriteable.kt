package dragapult.core

import java.io.File
import java.io.IOException

abstract class ExternalLocalizationHolderWriteable : ExternalLocalizationHolder() {

    @Throws(IOException::class)
    abstract fun write(file: File)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ExternalLocalizationHolderWriteable) return false

        if (source != other.source) return false

        return true
    }

    override fun hashCode(): Int {
        return source.hashCode()
    }

}