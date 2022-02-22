package dragapult.json.configuration

import dragapult.DragapultWriter
import dragapult.model.Translations
import org.json.JSONWriter
import java.io.File

class DragapultWriterConfiguration(
    private val file: File
) : DragapultWriter<Translations> {

    override fun write(input: Translations) {
        val writer = file.writer()
        val json = JSONWriter(writer)
        json.`object`()
        for ((key, localizations) in input)
            json.jsonObject(key) {
                for ((locale, value) in localizations)
                    json.value(locale.language, value)
            }
        json.endObject()
        writer.flush()
    }

}

inline fun JSONWriter.jsonObject(key: String, writer: JSONWriter.() -> Unit) {
    key(key)
    `object`()
    writer()
    endObject()
}

fun JSONWriter.value(key: String, value: String) {
    key(key)
    value(value)
}