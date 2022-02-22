package dragapult

import dragapult.model.Localization
import dragapult.model.Translations

class DragapultExporter private constructor(
    private val reader: DragapultReader<Translations>,
    private val converter: DragapultConverterFactory,
    private val writer: DragapultWriter<Localization>
) {

    fun run() {
        val translations = reader.read()
        val localizations = converter.getLocalization(translations).convert(translations)
        for (localization in localizations)
            writer.write(localization)
    }

    class Builder {

        private lateinit var reader: DragapultReader<Translations>
        private lateinit var converter: DragapultConverterFactory
        private lateinit var writer: DragapultWriter<Localization>

        fun setReader(reader: DragapultReader<Translations>) = apply {
            this.reader = reader
        }

        fun setConverter(converter: DragapultConverterFactory) = apply {
            this.converter = converter
        }

        fun setWriter(writer: DragapultWriter<Localization>) = apply {
            this.writer = writer
        }

        fun build(): DragapultExporter = DragapultExporter(
            reader = reader,
            converter = converter,
            writer = writer
        )

    }

}