package dragapult.android

import dragapult.DragapultConverter
import java.io.File
import java.util.*

class DragapultConverterLocaleAndroid(
    private val fileName: String = "strings.xml",
    private val defaultLanguage: String = "en"
) : DragapultConverter<File, Set<Locale>> {

    override fun convert(input: File) = getEligibleFolders(input)
        .asSequence()
        .map { it.nameWithoutExtension }
        .map { it.replace(Regex("values-?"), "") }
        .map { it.ifBlank { defaultLanguage } }
        .map { Locale.forLanguageTag(it) }
        .toSet()

    private fun getEligibleFolders(sourceDir: File): Array<out File> {
        fun containsSourceFile(file: File) = file.list().orEmpty().contains(fileName)
        return sourceDir.listFiles(::containsSourceFile).orEmpty()
    }

}