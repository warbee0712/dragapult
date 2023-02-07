package dragapult.core

import dragapult.core.tooling.loadServices
import java.io.File

typealias Key = String
typealias Value = String

interface LocalizationWriter {

    fun write(values: Sequence<Pair<Key, Value>>)

    interface Factory {

        val type: LocalizationType
        fun create(file: File): LocalizationWriter

    }

    companion object {

        fun File.localizationWriter(type: LocalizationType): LocalizationWriter {
            return loadServices<Factory>().first { it.type == type }.create(this)
        }

    }

}