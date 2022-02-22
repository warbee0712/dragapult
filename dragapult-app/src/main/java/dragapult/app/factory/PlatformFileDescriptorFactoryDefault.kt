package dragapult.app.factory

import dragapult.PlatformFileDescriptor
import dragapult.PlatformFileDescriptorDirectoryGuard
import dragapult.PlatformFileDescriptorFactory
import dragapult.PlatformFileDescriptorFileGuard
import dragapult.android.PlatformFileDescriptorAndroid
import dragapult.app.model.Platform
import dragapult.apple.PlatformFileDescriptorApple
import dragapult.json.PlatformFileDescriptorJson
import java.util.*

class PlatformFileDescriptorFactoryDefault(
    private val platform: Platform
) : PlatformFileDescriptorFactory {

    override fun getInstance(locale: Locale): PlatformFileDescriptor {
        var descriptor = when (platform) {
            Platform.Android -> PlatformFileDescriptorAndroid(locale)
            Platform.Apple -> PlatformFileDescriptorApple(locale)
            Platform.Json -> PlatformFileDescriptorJson(locale)
        }
        descriptor = PlatformFileDescriptorDirectoryGuard(descriptor)
        descriptor = PlatformFileDescriptorFileGuard(descriptor)
        return descriptor
    }

}