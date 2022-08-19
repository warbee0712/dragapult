package dragapult.android.factory

import com.google.auto.service.AutoService
import dragapult.android.LocalizationTypeAndroid
import dragapult.android.adapter.PlatformLocalizedFileWriteableAndroidAdapter
import dragapult.core.LocalizationType
import dragapult.core.PlatformLocalizedFile
import dragapult.core.PlatformLocalizedFileWriteable

@AutoService(PlatformLocalizedFileWriteable.Factory::class)
class PlatformLocalizedFileWriteableFactoryAndroid : PlatformLocalizedFileWriteable.Factory {

    override val type: LocalizationType
        get() = LocalizationTypeAndroid

    override fun writeable(file: PlatformLocalizedFile): PlatformLocalizedFileWriteable {
        return PlatformLocalizedFileWriteableAndroidAdapter(file)
    }

}