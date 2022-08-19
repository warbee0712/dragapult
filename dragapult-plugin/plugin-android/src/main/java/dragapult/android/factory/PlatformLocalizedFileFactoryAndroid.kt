package dragapult.android.factory

import com.google.auto.service.AutoService
import dragapult.android.LocalizationTypeAndroid
import dragapult.android.adapter.PlatformLocalizedFileAndroidAdapter
import dragapult.core.LocalizationType
import dragapult.core.PlatformLocalizedFile
import java.io.File
import java.nio.file.Files

@AutoService(PlatformLocalizedFile.Factory::class)
class PlatformLocalizedFileFactoryAndroid : PlatformLocalizedFile.Factory {

    override val type: LocalizationType
        get() = LocalizationTypeAndroid

    override fun fromFile(file: File): PlatformLocalizedFile {
        return PlatformLocalizedFileAndroidAdapter(file)
    }

    override fun fromDirectory(directory: File): Sequence<PlatformLocalizedFile> {
        require(directory.name == "res") {
            "You should point the program to ../src/main/res directory"
        }
        return directory.listFiles().orEmpty()
            .asSequence()
            .filter { it.name.startsWith("values") }
            .flatMap { it.listFiles().orEmpty().asSequence() }
            .filter { Files.probeContentType(it.toPath()) == type.mimeType }
            .map(::fromFile)
    }

}