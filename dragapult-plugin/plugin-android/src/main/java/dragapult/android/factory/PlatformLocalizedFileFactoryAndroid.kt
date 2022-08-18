package dragapult.android.factory

import com.google.auto.service.AutoService
import dragapult.android.LocalizationTypeAndroid
import dragapult.android.adapter.PlatformLocalizedFileAndroidAdapter
import dragapult.android.adapter.PlatformLocalizedFileWriteableAndroidAdapter
import dragapult.core.LocalizationType
import dragapult.core.PlatformLocalizedFile
import dragapult.core.PlatformLocalizedFileWriteable
import dragapult.core.factory.PlatformLocalizedFileFactory
import java.io.File
import java.nio.file.Files

@AutoService(PlatformLocalizedFileFactory::class)
class PlatformLocalizedFileFactoryAndroid : PlatformLocalizedFileFactory {

    override val type: LocalizationType
        get() = LocalizationTypeAndroid

    override fun fromFile(file: File): PlatformLocalizedFile {
        return PlatformLocalizedFileAndroidAdapter(file)
    }

    override fun fromDirectory(directory: File): Sequence<PlatformLocalizedFile> {
        return directory.listFiles().orEmpty()
            .asSequence()
            .filter { it.name.startsWith("values") }
            .flatMap { it.listFiles().orEmpty().asSequence() }
            .filter { Files.probeContentType(it.toPath()) == type.mimeType }
            .map(::fromFile)
    }

    override fun writeable(file: PlatformLocalizedFile): PlatformLocalizedFileWriteable {
        return PlatformLocalizedFileWriteableAndroidAdapter(file)
    }

}