package dragapult

import dragapult.model.Localization
import dragapult.model.Translations

class DragapultImporter private constructor(
    private val reader: DragapultReader<List<Localization>>,
    private val converter: DragapultConverter<List<Localization>, Translations>,
    private val writer: DragapultWriter<Translations>
) {

    fun run() {
        val localizations = reader.read()
        val translations = converter.convert(localizations)
        writer.write(translations)
    }

    class Builder {

        private lateinit var reader: DragapultReader<List<Localization>>
        private lateinit var converter: DragapultConverter<List<Localization>, Translations>
        private lateinit var writer: DragapultWriter<Translations>

        fun setReader(reader: DragapultReader<List<Localization>>) = apply {
            this.reader = reader
        }

        fun setConverter(converter: DragapultConverter<List<Localization>, Translations>) = apply {
            this.converter = converter
        }

        fun setWriter(writer: DragapultWriter<Translations>) = apply {
            this.writer = writer
        }

        fun build(): DragapultImporter = DragapultImporter(
            reader = reader,
            converter = converter,
            writer = writer
        )

    }

}