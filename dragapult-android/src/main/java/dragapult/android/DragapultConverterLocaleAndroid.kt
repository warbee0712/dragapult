package dragapult.android

import dragapult.DragapultConverter
import java.io.File
import java.util.*

class DragapultConverterLocaleAndroid(
    private val fileName: String = "strings.xml",
    private val defaultLanguage: String = "en"
) : DragapultConverter<File, Set<Locale>> {

    override fun convert(input: File): Set<Locale> {
        val eligibleFolders = input.listFiles { file ->
            file.list()?.contains(fileName) == true
        }.orEmpty()
        return eligibleFolders
            .asSequence()
            .map { it.nameWithoutExtension }
            .map { it.replace(Regex("values-?"), "") }
            .map { it.ifBlank { defaultLanguage } }
            .map { Locale.forLanguageTag(it) }
            .toSet()
    }

}