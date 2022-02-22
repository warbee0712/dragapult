package dragapult.android

import dragapult.PlatformFileDescriptor
import java.io.File
import java.util.*

class PlatformFileDescriptorAndroid(
    private val locale: Locale,
    private val fileName: String = "strings.xml",
    private val defaultLanguage: String = "en"
) : PlatformFileDescriptor {

    override fun getHeader() = StringBuilder()
        .appendLine("""<?xml version="1.0" encoding="utf-8"?>""")
        .appendLine("<resources>")
        .toString()

    override fun getFooter() = StringBuilder()
        .appendLine("</resources>")
        .toString()

    override fun getLineBlueprint() = StringBuilder("\t")
        .append("""<string name="%s">""")
        .append("%s")
        .append("""</string>""")
        .toString()

    override fun getLineSeparator(isLast: Boolean): String = System.lineSeparator()

    override fun getFile(directory: File): File {
        val name = when (locale.language) {
            defaultLanguage -> "values"
            else -> "values-${locale.language}"
        }
        val localeDir = File(directory, name)
        return File(localeDir, fileName)
    }

}