package dragapult.json.configuration

import dragapult.DragapultReader
import dragapult.model.Translations
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File
import java.util.*

class DragapultReaderConfiguration(
    private val file: File
) : DragapultReader<Translations> {

    override fun read(): Translations {
        val reader = file.reader()
        val tokener = JSONTokener(reader)
        val contents = JSONObject(tokener)
        val output = mutableMapOf<String, MutableMap<Locale, String>>()

        for (key in contents.keys()) {
            val translations = contents.getJSONObject(key)
            for (language in translations.keys()) {
                val locales = output.getOrPut(key) { mutableMapOf() }
                val locale = Locale.forLanguageTag(language)
                locales[locale] = translations.getString(language)
            }
        }

        return output
    }

}
