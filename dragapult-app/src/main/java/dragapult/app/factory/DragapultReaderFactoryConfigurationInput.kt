package dragapult.app.factory

import dragapult.DragapultReader
import dragapult.app.model.Source
import dragapult.model.Translations

class DragapultReaderFactoryConfigurationInput(
    private val json: DragapultReader<Translations>,
    private val csv: DragapultReader<Translations>
) : DragapultReaderFactoryConfiguration<Translations> {

    override fun getInstance(type: Source) = when (type) {
        Source.Json -> json
        Source.Csv -> csv
    }

}