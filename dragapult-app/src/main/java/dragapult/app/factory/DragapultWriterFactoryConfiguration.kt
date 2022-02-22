package dragapult.app.factory

import dragapult.DragapultWriter
import dragapult.app.model.Source

interface DragapultWriterFactoryConfiguration<Input> {

    fun getInstance(type: Source): DragapultWriter<Input>

}