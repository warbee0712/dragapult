package dragapult.csv.configuration

import dragapult.DragapultReader
import dragapult.model.Translations
import org.apache.commons.csv.CSVFormat
import java.io.File
import java.util.*

class DragapultReaderConfiguration(
    private val file: File
) : DragapultReader<Translations> {

    override fun read(): Translations {
        val reader = file.reader()
        val contents = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)
        val output = mutableMapOf<String, MutableMap<Locale, String>>()

        for (record in contents) {
            val locales = output.getOrPut(record[0]) { mutableMapOf() }
            for ((language, index) in contents.headerMap) {
                if (index == 0) continue
                val locale = Locale.forLanguageTag(language)
                locales[locale] = record.get(index)
            }
        }

        return output
    }

}
