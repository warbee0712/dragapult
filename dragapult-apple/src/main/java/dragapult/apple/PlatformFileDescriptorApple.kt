package dragapult.apple

import dragapult.PlatformFileDescriptor
import java.io.File
import java.util.*

class PlatformFileDescriptorApple(
    private val locale: Locale,
    private val fileName: String = "Localizable.strings"
) : PlatformFileDescriptor {

    override fun getHeader() = ""

    override fun getFooter() = ""

    override fun getLineBlueprint() = StringBuilder("\"%s\"")
        .append("=")
        .append("\"%s\";")
        .toString()

    override fun getLineSeparator(isLast: Boolean): String = System.lineSeparator()

    override fun getFile(directory: File): File {
        val localeDir = File(directory, "${locale.language}.lproj")
        return File(localeDir, fileName)
    }

}