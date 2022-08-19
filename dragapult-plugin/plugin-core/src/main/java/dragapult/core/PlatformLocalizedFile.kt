package dragapult.core

import dragapult.core.tooling.loadServices
import java.io.File
import java.util.*

abstract class PlatformLocalizedFile : Model {

    abstract val locale: Locale
    abstract val values: Sequence<LocalizedPair>

    val keys
        get() = values.map { it.key }.distinct()

    interface Factory {

        val type: LocalizationType

        fun fromFile(file: File): PlatformLocalizedFile
        fun fromDirectory(directory: File): Sequence<PlatformLocalizedFile>

        companion object {

            fun fromFile(file: File, type: LocalizationType): PlatformLocalizedFile {
                return loadServices<Factory>().first { it.type == type }.fromFile(file)
            }

            fun fromDirectory(file: File, type: LocalizationType): Sequence<PlatformLocalizedFile> {
                return loadServices<Factory>().first { it.type == type }.fromDirectory(file)
            }

        }

    }

    companion object {

        operator fun invoke(
            locale: Locale,
            values: Iterable<LocalizedPair>
        ): PlatformLocalizedFile = PlatformLocalizedFileDefault(
            locale = locale,
            values = values.asSequence()
        )

    }

}

private data class PlatformLocalizedFileDefault(
    override val locale: Locale,
    override val values: Sequence<LocalizedPair>
) : PlatformLocalizedFile()