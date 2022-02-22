package dragapult

import java.util.*

interface PlatformFileDescriptorFactory {

    fun getInstance(locale: Locale): PlatformFileDescriptor

}