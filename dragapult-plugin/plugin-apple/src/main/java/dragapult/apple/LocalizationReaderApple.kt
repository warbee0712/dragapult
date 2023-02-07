package dragapult.apple

import com.google.auto.service.AutoService
import dragapult.core.Key
import dragapult.core.LocalizationReader
import dragapult.core.LocalizationType
import dragapult.core.Value
import java.io.File

class LocalizationReaderApple(
    private val file: File
) : LocalizationReader {

    override fun read(): Sequence<Pair<Key, Value>> {
        return file.bufferedReader().lineSequence()
            .map { Regex("\"(.+)\"\\s*=\\s*\"(.+)\";").find(it)?.groupValues.orEmpty().drop(1) }
            .filter { it.size >= 2 }
            .map { (key, value) -> key to value }
    }

    @AutoService(LocalizationReader.Factory::class)
    class Factory : LocalizationReader.Factory {

        override val type: LocalizationType
            get() = LocalizationTypeApple

        override fun create(file: File): LocalizationReader {
            return LocalizationReaderApple(file)
        }

    }

}