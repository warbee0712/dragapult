package dragapult.apple.factory

import com.google.auto.service.AutoService
import dragapult.apple.LocalizationTypeApple
import dragapult.apple.adapter.PlatformLocalizedFileAppleAdapter
import dragapult.core.LocalizationType
import dragapult.core.PlatformLocalizedFile
import java.io.File

@AutoService(PlatformLocalizedFile.Factory::class)
class PlatformLocalizedFileFactoryApple : PlatformLocalizedFile.Factory {

    override val type: LocalizationType
        get() = LocalizationTypeApple

    override fun fromFile(file: File): PlatformLocalizedFile {
        return PlatformLocalizedFileAppleAdapter(file)
    }

    override fun fromDirectory(directory: File): Sequence<PlatformLocalizedFile> {
        require(directory.name == "resources") {
            "You should point the program to ../shared/resources directory"
        }
        return directory.listFiles().orEmpty()
            .asSequence()
            .filter { it.name.endsWith(".lproj") }
            .flatMap { it.listFiles().orEmpty().asSequence() }
            .filter { it.endsWith(".strings") }
            .map(::fromFile)
    }

}