package dragapult.core

import dragapult.core.tooling.loadServices
import java.io.File

interface LocalizationWriter {

    fun write(values: Sequence<Pair<String, String>>)

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