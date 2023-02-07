package dragapult.json

import com.google.auto.service.AutoService
import dragapult.core.Key
import dragapult.core.LocalizationReader
import dragapult.core.LocalizationType
import dragapult.core.Value
import dragapult.json.tooling.jsonObject
import java.io.File

class LocalizationReaderJson(
    private val file: File
) : LocalizationReader {

    override fun read(): Sequence<Pair<Key, Value>> {
        return sequence {
            val json = file.jsonObject()
            for (key in json.keys()) {
                yield(key to json.getString(key))
            }
        }
    }

    @AutoService(LocalizationReader.Factory::class)
    class Factory : LocalizationReader.Factory {

        override val type: LocalizationType
            get() = LocalizationTypeJson

        override fun create(file: File): LocalizationReader {
            return LocalizationReaderJson(file)
        }

    }

}