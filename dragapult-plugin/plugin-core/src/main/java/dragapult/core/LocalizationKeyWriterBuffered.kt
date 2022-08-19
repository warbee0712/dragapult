package dragapult.core

import java.util.*

abstract class LocalizationKeyWriterBuffered : LocalizationKeyWriter {

    protected val lines = mutableMapOf<String, MutableSet<Translation>>()

    override fun write(key: String, locale: Locale, value: String) {
        lines.getOrPut(key) { mutableSetOf() } += Translation(locale, value)
    }

    override fun close() {
        lines.clear()
    }

    // ---

    data class Translation(
        val locale: Locale,
        val value: String
    )

}