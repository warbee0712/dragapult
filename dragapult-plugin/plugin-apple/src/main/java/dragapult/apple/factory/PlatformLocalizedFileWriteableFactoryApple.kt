package dragapult.apple.factory

import com.google.auto.service.AutoService
import dragapult.apple.LocalizationTypeApple
import dragapult.apple.adapter.PlatformLocalizedFileWriteableAppleAdapter
import dragapult.core.LocalizationType
import dragapult.core.PlatformLocalizedFile
import dragapult.core.PlatformLocalizedFileWriteable

@AutoService(PlatformLocalizedFileWriteable.Factory::class)
class PlatformLocalizedFileWriteableFactoryApple : PlatformLocalizedFileWriteable.Factory {

    override val type: LocalizationType
        get() = LocalizationTypeApple

    override fun writeable(file: PlatformLocalizedFile): PlatformLocalizedFileWriteable {
        return PlatformLocalizedFileWriteableAppleAdapter(file)
    }

}