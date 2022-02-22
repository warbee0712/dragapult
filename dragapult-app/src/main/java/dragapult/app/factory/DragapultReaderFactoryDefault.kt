package dragapult.app.factory

import dragapult.DragapultConverter
import dragapult.DragapultReaderFactory
import dragapult.PlatformFileDescriptorFactory
import dragapult.android.DragapultReaderAndroid
import dragapult.app.model.Platform
import dragapult.apple.DragapultReaderApple
import dragapult.json.DragapultReaderJson
import java.io.File
import java.util.*

class DragapultReaderFactoryDefault(
    private val platform: Platform,
    private val converter: DragapultConverter<File, Set<Locale>>,
    private val descriptorFactory: PlatformFileDescriptorFactory
) : DragapultReaderFactory {

    override fun getInstances(directory: File) = converter.convert(directory).map {
        val descriptor = descriptorFactory.getInstance(it)
        when (platform) {
            Platform.Android -> DragapultReaderAndroid(it, descriptor.getFile(directory))
            Platform.Apple -> DragapultReaderApple(it, descriptor.getFile(directory))
            Platform.Json -> DragapultReaderJson(it, descriptor.getFile(directory))
        }
    }
}