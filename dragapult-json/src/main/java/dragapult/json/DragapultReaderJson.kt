package dragapult.json

import dragapult.DragapultReader
import dragapult.model.Key
import dragapult.model.Localization
import dragapult.model.Value
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File
import java.util.*

class DragapultReaderJson(
    private val locale: Locale,
    private val file: File
) : DragapultReader<Localization> {

    override fun read(): Localization {
        val reader = file.reader()
        val tokener = JSONTokener(reader)
        val jsonValues = JSONObject(tokener)
        val values = mutableMapOf<Key, Value>()
        for ((key, value) in jsonValues) {
            values[key] = value.toString()
        }
        return Localization(locale, values)
    }

}

operator fun JSONObject.iterator() = iterator<Pair<String, Any>> {
    for (key in keys()) {
        yield(key to get(key))
    }
}