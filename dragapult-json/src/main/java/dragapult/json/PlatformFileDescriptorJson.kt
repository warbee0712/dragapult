package dragapult.json

import dragapult.PlatformFileDescriptor
import java.io.File
import java.util.*

class PlatformFileDescriptorJson(
    private val locale: Locale
) : PlatformFileDescriptor {

    override fun getHeader(): String {
        return "{"
    }

    override fun getFooter(): String {
        return "}"
    }

    override fun getLineBlueprint() = StringBuilder("\t")
        .append("\"%s\"")
        .append(":")
        .append("\"%s\"")
        .toString()

    override fun getLineSeparator(isLast: Boolean): String {
        return when (isLast) {
            true -> ""
            else -> "," + System.lineSeparator()
        }
    }

    override fun getFile(directory: File): File {
        return File(directory, locale.language + ".json")
    }

}