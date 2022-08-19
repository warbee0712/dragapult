package dragapult.core.adapter

import dragapult.core.LocalizationType
import dragapult.core.LocalizationWriter.Companion.localizationWriter
import dragapult.core.PlatformLocalizedFile
import dragapult.core.PlatformLocalizedFileWriteable
import java.io.File

abstract class AbstractPlatformLocalizedFileWriteableAdapter(
    origin: PlatformLocalizedFile
) : PlatformLocalizedFileWriteable(origin) {

    protected abstract val type: LocalizationType
    protected abstract val fileName: String

    override fun write(directory: File) {
        getOutputFile(directory)
            .localizationWriter(type)
            .write(values.map { it.key to it.value })
    }

    private fun getOutputFile(directory: File): File {
        val file = File(directory, fileName)
        val parent = file.parentFile.let(::requireNotNull)
        parent.mkdirs()
        file.delete()
        file.createNewFile()
        return file
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractPlatformLocalizedFileWriteableAdapter) return false
        if (!super.equals(other)) return false

        if (type != other.type) return false
        if (fileName != other.fileName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + fileName.hashCode()
        return result
    }


}