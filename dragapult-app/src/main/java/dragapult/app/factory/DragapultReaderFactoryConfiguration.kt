package dragapult.app.factory

import dragapult.DragapultReader
import dragapult.app.model.Source

interface DragapultReaderFactoryConfiguration<Output> {

    fun getInstance(type: Source): DragapultReader<Output>

}