package dragapult.apple

import com.google.auto.service.AutoService
import dragapult.core.LocalizationType
import dragapult.core.LocalizationWriter
import dragapult.core.LocalizationWriterReplacing
import java.io.File

class LocalizationWriterApple(
    private val file: File
) : LocalizationWriter {

    override fun write(values: Sequence<Pair<String, String>>) {
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
            return writer
        }

    }

}