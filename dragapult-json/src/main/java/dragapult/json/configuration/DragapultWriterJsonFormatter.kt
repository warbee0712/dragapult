package dragapult.json.configuration

import dragapult.DragapultWriter
import dragapult.model.Translations
import org.json.JSONObject
import java.io.File

class DragapultWriterJsonFormatter(
    private val file: File
) : DragapultWriter<Translations> {

    override fun write(input: Translations) {
        val jsonText = file.readText()
        val json = JSONObject(jsonText)
        val jsonPrettified = json.toString(4)
        file.writeText(jsonPrettified)
    }

}
