package dragapult.android.adapter

import dragapult.android.AndroidFileWriter.Companion.androidWriter
import dragapult.core.PlatformLocalizedFile
import dragapult.core.PlatformLocalizedFileWriteable
import java.io.File

class PlatformLocalizedFileWriteableAndroidAdapter(
    origin: PlatformLocalizedFile
) : PlatformLocalizedFileWriteable(origin) {

    private val dirName
        get() = when (val l = locale.language) {
            "en" -> "values"
            else -> "values-$l"
        }

    override fun write(directory: File) {
        getOutputFile(directory)
            .androidWriter()
            .write(values.map { it.key to it.value })
    }

    private fun getOutputFile(directory: File): File {
        val file = File(directory, "$dirName/strings.xml")
        val parent = file.parentFile.let(::requireNotNull)
        parent.mkdirs()
        file.delete()
        file.createNewFile()
        return file
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlatformLocalizedFileWriteableAndroidAdapter

        return super.equals(other)
    }

    @Suppress("RedundantOverride")
    override fun hashCode(): Int {
        return super.hashCode()
    }

}