package dragapult.core

import dragapult.core.tooling.loadServices
import java.io.File
import java.io.IOException

abstract class ExternalLocalizationHolderWriteable {

    abstract val source: Sequence<ExternalLocalizationHolder>

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

    interface Factory {

        val type: LocalizationType

        fun writeable(holders: Sequence<ExternalLocalizationHolder>): ExternalLocalizationHolderWriteable

        companion object {

            fun Sequence<ExternalLocalizationHolder>.asWriteable(
                type: LocalizationType
            ): ExternalLocalizationHolderWriteable {
                return loadServices<Factory>().first { it.type == type }.writeable(this)
            }

        }

    }

}