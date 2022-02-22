package dragapult.app.factory

import dragapult.DragapultConverter
import dragapult.DragapultLocaleConverterFactory
import dragapult.android.DragapultConverterLocaleAndroid
import dragapult.app.model.Platform
import dragapult.apple.DragapultConverterLocaleApple
import dragapult.json.DragapultConverterLocaleJson
import java.io.File
import java.util.*

class DragapultLocaleConverterFactoryDefault(
    private val platform: Platform
) : DragapultLocaleConverterFactory {
    override fun getInstance(): DragapultConverter<File, Set<Locale>> {
        return when (platform) {
            Platform.Android -> DragapultConverterLocaleAndroid()
            Platform.Apple -> DragapultConverterLocaleApple()
            Platform.Json -> DragapultConverterLocaleJson()
        }
    }
}