package dragapult.core

import dragapult.core.tooling.loadServices
import java.io.File
import java.io.IOException
import java.util.*

abstract class PlatformLocalizedFileWriteable(
    private val origin: PlatformLocalizedFile
) : PlatformLocalizedFile() {

    override val locale: Locale
        get() = origin.locale

    override val values: Sequence<LocalizedPair>
        get() = origin.values

    @Throws(IOException::class)
    abstract fun write(directory: File)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PlatformLocalizedFileWriteable) return false
        if (!super.equals(other)) return false

        if (origin != other.origin) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + origin.hashCode()
        return result
    }

    interface Factory {

        val type: LocalizationType

        fun writeable(file: PlatformLocalizedFile): PlatformLocalizedFileWriteable

        companion object {

            fun PlatformLocalizedFile.asWriteable(type: LocalizationType): PlatformLocalizedFileWriteable {
                return loadServices<Factory>().first { it.type == type }.writeable(this)
            }

        }

    }

}