package dragapult.core.adapter

import dragapult.core.LocalizationReader.Companion.localizationReader
import dragapult.core.LocalizationType
import dragapult.core.LocalizedPair
import dragapult.core.PlatformLocalizedFile
import java.io.File

abstract class AbstractPlatformLocalizedFileAdapter : PlatformLocalizedFile() {

    protected abstract val file: File
    protected abstract val type: LocalizationType

    override val values: Sequence<LocalizedPair>
        get() = file
            .localizationReader(type).read()
            .map { (key, value) -> LocalizedPair(key, value) }

}