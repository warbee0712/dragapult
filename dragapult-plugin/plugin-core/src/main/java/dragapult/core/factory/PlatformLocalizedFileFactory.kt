package dragapult.core.factory

import dragapult.core.LocalizationType
import dragapult.core.PlatformLocalizedFile
import dragapult.core.PlatformLocalizedFileWriteable
import dragapult.core.tooling.loadServices
import java.io.File
import java.nio.file.Files
import javax.management.ServiceNotFoundException

interface PlatformLocalizedFileFactory {

    val type: LocalizationType

    fun fromFile(file: File): PlatformLocalizedFile
    fun fromDirectory(directory: File): Sequence<PlatformLocalizedFile>

    fun writeable(file: PlatformLocalizedFile): PlatformLocalizedFileWriteable

    companion object {

        fun File.asExternalLocalizedFile(): PlatformLocalizedFile {
            val factories = loadServices<PlatformLocalizedFileFactory>()
            val mimeType = Files.probeContentType(toPath())
            val factory = factories.find { it.type.mimeType == mimeType }
                ?: throw ServiceNotFoundException("Couldn't find ExternalLocalizedFileFactory for type '$mimeType'")
            return factory.fromFile(this)
        }

        fun File.asExternalLocalizedFiles(type: LocalizationType): Sequence<PlatformLocalizedFile> {
            val factories = loadServices<PlatformLocalizedFileFactory>()
            val factory = factories.find { it.type == type }
                ?: throw ServiceNotFoundException("Couldn't find ExternalLocalizedFileFactory for type '$type'")
            return factory.fromDirectory(this)
        }

    }

}