package dragapult

import dragapult.model.Localization
import dragapult.model.Translations

class DragapultImporter(
    private val reader: DragapultReader<List<Localization>>,
    private val converter: DragapultConverter<List<Localization>, Translations>,
    private val writer: DragapultWriter<Translations>
) {

    fun run() {
        val localizations = reader.read()
        val translations = converter.convert(localizations)
        writer.write(translations)
    }

}