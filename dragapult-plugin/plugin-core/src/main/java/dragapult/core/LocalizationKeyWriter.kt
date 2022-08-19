package dragapult.core

import dragapult.core.tooling.loadServices
import java.io.File
import java.util.*

interface LocalizationKeyWriter : AutoCloseable {

    fun write(key: String, locale: Locale, value: String)

    interface Factory {

        val type: LocalizationType
        fun create(file: File): LocalizationKeyWriter

    }

    companion object {

        fun File.asLocalizationKeyWriter(type: LocalizationType): LocalizationKeyWriter {
            return loadServices<Factory>().first { it.type == type }.create(this)
        }

    }

}