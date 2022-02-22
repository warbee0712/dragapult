package dragapult.app.factory

import dragapult.DragapultWriter
import dragapult.app.model.Source
import dragapult.model.Translations

class DragapultWriterFactoryConfigurationOutput(
    private val json: DragapultWriter<Translations>,
    private val csv: DragapultWriter<Translations>
) : DragapultWriterFactoryConfiguration<Translations> {

    override fun getInstance(type: Source): DragapultWriter<Translations> = when (type) {
        Source.Json -> json
        Source.Csv -> csv
    }

}