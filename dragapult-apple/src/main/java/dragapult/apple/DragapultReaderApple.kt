package dragapult.apple

import dragapult.DragapultReader
import dragapult.model.Localization
import java.io.File
import java.util.*

class DragapultReaderApple(
    private val locale: Locale,
    private val file: File
) : DragapultReader<Localization> {

    override fun read(): Localization {
        val pattern = Regex("\"([\\da-zA-Z_\\-]+)\"\\s*=\\s*\"(.+)\"")
        val values = file.readLines().asSequence()
            .mapNotNull { pattern.find(it) }
            .map { it.groupValues[1] to it.groupValues[2] }
            .toMap()
        return Localization(locale, values)
    }

}