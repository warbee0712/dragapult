package dragapult.core

import dragapult.core.tooling.loadServices
import java.io.File
import java.util.*

interface LocalizationKeyReader {

    val keys: Sequence<String>

    fun read(key: String): Sequence<Pair<Locale, String>>

    interface Factory {

        val type: LocalizationType
        fun create(file: File): LocalizationKeyReader

    }

    companion object {

        fun File.asLocalizationKeyReader(type: LocalizationType): LocalizationKeyReader {
            return loadServices<Factory>().first { it.type == type }.create(this)
        }

    }

}