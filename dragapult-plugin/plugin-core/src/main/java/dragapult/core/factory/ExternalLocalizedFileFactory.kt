package dragapult.core.factory

import dragapult.core.ExternalLocalizedFile
import dragapult.core.LocalizationType
import dragapult.core.tooling.loadServices
import java.io.File
import java.nio.file.Files
import javax.management.ServiceNotFoundException

interface ExternalLocalizedFileFactory {

    val type: LocalizationType

    fun fromFile(file: File): Iterable<ExternalLocalizedFile>
    fun fromDirectory(directory: File): Iterable<ExternalLocalizedFile>

    companion object {

        fun File.asExternalLocalizedFiles(): Iterable<ExternalLocalizedFile> {
            val factories = loadServices<ExternalLocalizedFileFactory>()
            val mimeType = Files.probeContentType(toPath())
            val factory = factories.find { it.type.mimeType == mimeType }
                ?: throw ServiceNotFoundException("Couldn't find ExternalLocalizedFileFactory for type '$mimeType'")
            return factory.fromFile(this)
        }

        fun File.asExternalLocalizedFiles(type: LocalizationType): Iterable<ExternalLocalizedFile> {
            val factories = loadServices<ExternalLocalizedFileFactory>()
            val factory = factories.find { it.type == type }
                ?: throw ServiceNotFoundException("Couldn't find ExternalLocalizedFileFactory for type '$type'")
            return factory.fromDirectory(this)
        }

    }

}