package dragapult.json

import com.google.auto.service.AutoService
import dragapult.core.LocalizationKeyWriter
import dragapult.core.LocalizationType
import org.json.JSONObject
import java.io.File
import java.util.*

class LocalizationKeyWriterJson(
    private val file: File
) : LocalizationKeyWriter {

    private val data = JSONObject()

    override fun write(key: String, locale: Locale, value: String) {
        data.getOrPutJSONObject(key).put(locale.language, value)
    }

    override fun close() {
        file.writer().use {
            data.write(it, 4, 0)
            data.clear()
        }
    }

    // ---

    private fun JSONObject.getOrPutJSONObject(key: String): JSONObject {
        return optJSONObject(key) ?: JSONObject().also {
            put(key, it)
        }
    }

    // ---

    @AutoService(LocalizationKeyWriter.Factory::class)
    class Factory : LocalizationKeyWriter.Factory {

        override val type: LocalizationType
            get() = LocalizationTypeJson

        override fun create(file: File): LocalizationKeyWriter {
            return LocalizationKeyWriterJson(file)
        }

    }

}