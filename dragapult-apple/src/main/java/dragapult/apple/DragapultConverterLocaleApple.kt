package dragapult.apple

import dragapult.DragapultConverter
import java.io.File
import java.util.*

class DragapultConverterLocaleApple(
    private val fileName: String = "Localizable.strings",
    private val defaultLanguage: String = "en"
) : DragapultConverter<File, Set<Locale>> {

    override fun convert(input: File): Set<Locale> {
        val eligibleFolders = input.listFiles { file ->
            file.list()?.contains(fileName) == true
        }
        return eligibleFolders
            .asSequence()
            .map { it.nameWithoutExtension }
            .map { it.replace("\\.lproj", "") }
            .map { it.ifBlank { defaultLanguage } }
            .map { Locale.forLanguageTag(it) }
            .toSet()
    }

}