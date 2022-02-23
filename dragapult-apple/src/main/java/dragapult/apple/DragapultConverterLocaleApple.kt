package dragapult.apple

import dragapult.DragapultConverter
import java.io.File
import java.util.*

class DragapultConverterLocaleApple(
    private val fileName: String = "Localizable.strings",
    private val defaultLanguage: String = "en"
) : DragapultConverter<File, Set<Locale>> {

    override fun convert(input: File) = getEligibleFolders(input)
        .asSequence()
        .map { it.nameWithoutExtension }
        .map { it.replace("\\.lproj", "") }
        .map { it.ifBlank { defaultLanguage } }
        .map { Locale.forLanguageTag(it) }
        .toSet()

    private fun getEligibleFolders(sourceDir: File): Array<out File> {
        fun containsSourceFile(file: File) = file.list().orEmpty().contains(fileName)
        return sourceDir.listFiles(::containsSourceFile).orEmpty()
    }

}