package dragapult.csv.configuration

import dragapult.DragapultWriter
import dragapult.model.Translations
import dragapult.model.languages
import org.apache.commons.csv.CSVFormat
import java.io.File
import java.nio.charset.Charset

class DragapultWriterConfiguration(
    private val file: File
) : DragapultWriter<Translations> {

    override fun write(input: Translations) {
        val printer = CSVFormat.DEFAULT.print(file, Charset.defaultCharset())
        val keys = input.languages.toList()

        printer.printRecord(listOf("key") + keys.map { it.language })
        for ((key, localizations) in input) {
            val values = buildList {
                add(key)
                for ((index, locale) in keys.withIndex())
                    add(index + 1, localizations[locale].orEmpty())
            }
            printer.printRecord(values)
        }
        printer.close(true)
    }

}
