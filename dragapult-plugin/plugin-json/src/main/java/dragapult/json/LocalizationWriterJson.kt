package dragapult.json

import com.google.auto.service.AutoService
import dragapult.core.LocalizationType
import dragapult.core.LocalizationWriter
import org.json.JSONObject
import java.io.File

class LocalizationWriterJson(
    private val file: File
) : LocalizationWriter {

    override fun write(values: Sequence<Pair<String, String>>) {
        val json = JSONObject()
        for ((key, value) in values) {
            json.put(key, value)
        }
        file.writer().use {
            json.write(it)
        }
    }

    // ---

    @AutoService(LocalizationWriter.Factory::class)
    class Factory : LocalizationWriter.Factory {

        override val type: LocalizationType
            get() = LocalizationTypeJson

        override fun create(file: File): LocalizationWriter {
            return LocalizationWriterJson(file)
        }

    }

}