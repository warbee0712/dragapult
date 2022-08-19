package dragapult.csv

import com.google.auto.service.AutoService
import dragapult.core.LocalizationKeyWriter
import dragapult.core.LocalizationType
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.File
import java.nio.charset.Charset
import java.util.*

class LocalizationKeyWriterCsv(
    private val file: File
) : LocalizationKeyWriter {

    private val lines = mutableMapOf<String, MutableSet<Translation>>()

    private val locales
        get() = lines.entries.asSequence().flatMap { it.value }.map { it.locale }.distinct()

    private val headers
        get() = buildList {
            this += "key"
            addAll(locales.map { it.language })
        }

    // ---

    override fun write(key: String, locale: Locale, value: String) {
        lines.getOrPut(key) { mutableSetOf() } += Translation(locale, value)
    }

    override fun close() {
        file.csvPrinter().use { printer ->
            val headers = headers
            printer.printRecord(headers)
            printer.printRecords(lines.asSequence().map { it.asRecord(headers) }.asIterable())
        }
    }

    // ---

    private fun Map.Entry<String, MutableSet<Translation>>.asRecord(headers: List<String>): List<String> {
        return List(headers.size) { index ->
            when (index) {
                0 -> key
                else -> value.valueFor(Locale.forLanguageTag(headers[index])).orEmpty()
            }
        }
    }

    private fun Set<Translation>.valueFor(locale: Locale): String? {
        return firstOrNull { it.locale == locale }?.value
    }

    private fun File.csvPrinter(): CSVPrinter {
        val format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
            .setAutoFlush(true)
            .build()
        return format.print(this, Charset.defaultCharset())
    }

    // ---

    data class Translation(
        val locale: Locale,
        val value: String
    )

    @AutoService(LocalizationKeyWriter.Factory::class)
    class Factory : LocalizationKeyWriter.Factory {

        override val type: LocalizationType
            get() = LocalizationTypeCsv

        override fun create(file: File): LocalizationKeyWriter {
            return LocalizationKeyWriterCsv(file)
        }

    }

}