package dragapult.core.factory

import dragapult.core.ExternalLocalizationHolder
import dragapult.core.LocalizationType
import dragapult.core.tooling.loadServices
import java.io.File
import java.nio.file.Files
import javax.management.ServiceNotFoundException

interface ExternalLocalizationHolderFactory {

    val type: LocalizationType

    fun fromFile(file: File): Iterable<ExternalLocalizationHolder>

    companion object {

        fun File.asExternalLocalizationHolders(): Iterable<ExternalLocalizationHolder> {
            val factories = loadServices<ExternalLocalizationHolderFactory>()
            val mimeType = Files.probeContentType(toPath())
            val factory = factories.find { it.type.mimeType == mimeType }
                ?: throw ServiceNotFoundException("Couldn't find ExternalLocalizationHolderFactory for type '$mimeType'")
            return factory.fromFile(this)
        }

    }

}