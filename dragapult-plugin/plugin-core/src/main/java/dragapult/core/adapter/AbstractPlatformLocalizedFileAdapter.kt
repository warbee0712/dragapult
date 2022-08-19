package dragapult.core.adapter

import dragapult.core.LocalizationReader.Companion.localizationReader
import dragapult.core.LocalizationType
import dragapult.core.LocalizedPair
import dragapult.core.PlatformLocalizedFile
import java.io.File

abstract class AbstractPlatformLocalizedFileAdapter : PlatformLocalizedFile() {

    protected abstract val file: File
    protected abstract val type: LocalizationType

    override val values: Sequence<LocalizedPair>
        get() = file
            .localizationReader(type).read()
            .map { (key, value) -> LocalizedPair(key, value) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractPlatformLocalizedFileAdapter) return false
        if (!super.equals(other)) return false

        if (file != other.file) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + file.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }

}