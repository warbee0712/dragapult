package dragapult.json

import dragapult.DragapultConverter
import java.io.File
import java.util.*

class DragapultConverterLocaleJson(
    private val defaultLanguage: String = "en"
) : DragapultConverter<File, Set<Locale>> {

    override fun convert(input: File): Set<Locale> {
        return input.listFiles().orEmpty().asSequence()
            .map { it.nameWithoutExtension }
            .map { it.replace(".json", "") }
            .map { it.ifBlank { defaultLanguage } }
            .map { Locale.forLanguageTag(it) }
            .toSet()
    }

}