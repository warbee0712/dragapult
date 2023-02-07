package dragapult.apple

import com.google.auto.service.AutoService
import dragapult.core.*
import java.io.File

class LocalizationWriterApple(
    private val file: File
) : LocalizationWriter {

    override fun write(values: Sequence<Pair<Key, Value>>) {
        file.bufferedWriter().use {
            for ((key, value) in values) {
                it.write(asLine(key, value))
                it.newLine()
            }
        }
    }

    private fun asLine(key: String, value: String): String {
        return "\"%s\" = \"%s\";".format(key, value)
    }

    @AutoService(LocalizationWriter.Factory::class)
    class Factory : LocalizationWriter.Factory {

        override val type: LocalizationType
            get() = LocalizationTypeApple

        override fun create(file: File): LocalizationWriter {
            var writer: LocalizationWriter
            writer = LocalizationWriterApple(file)
            writer = LocalizationWriterReplacing(writer, "%s", "%@")
            writer = LocalizationWriterEmptyFiltering(writer)
            return writer
        }

    }

}