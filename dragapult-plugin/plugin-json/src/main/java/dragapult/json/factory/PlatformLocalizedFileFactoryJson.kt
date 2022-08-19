package dragapult.json.factory

import com.google.auto.service.AutoService
import dragapult.core.LocalizationType
import dragapult.core.PlatformLocalizedFile
import dragapult.json.LocalizationTypeJson
import dragapult.json.adapter.PlatformLocalizedFileJsonAdapter
import java.io.File

@AutoService(PlatformLocalizedFile.Factory::class)
class PlatformLocalizedFileFactoryJson : PlatformLocalizedFile.Factory {

    override val type: LocalizationType
        get() = LocalizationTypeJson

    override fun fromFile(file: File): PlatformLocalizedFile {
        return PlatformLocalizedFileJsonAdapter(file)
    }

    override fun fromDirectory(directory: File): Sequence<PlatformLocalizedFile> {
        return directory.listFiles().orEmpty().asSequence()
            .filter { it.endsWith(".json") }
            .map(::fromFile)
    }

}