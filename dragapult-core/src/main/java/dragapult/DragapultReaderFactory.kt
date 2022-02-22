package dragapult

import dragapult.model.Localization
import java.io.File

interface DragapultReaderFactory {
    fun getInstances(directory: File): List<DragapultReader<Localization>>
}