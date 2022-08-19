package dragapult.core

import dragapult.core.tooling.loadServices
import java.io.File

interface LocalizationReader {

    fun read(): Sequence<Pair<String, String>>

    interface Factory {

        val type: LocalizationType
        fun create(file: File): LocalizationReader

    }

    companion object {

        fun File.localizationReader(type: LocalizationType): LocalizationReader {
            return loadServices<Factory>().first { it.type == type }.create(this)
        }

    }

}
