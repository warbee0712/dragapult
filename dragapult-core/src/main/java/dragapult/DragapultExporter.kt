package dragapult

import dragapult.model.Localization
import dragapult.model.Translations

class DragapultExporter(
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

}