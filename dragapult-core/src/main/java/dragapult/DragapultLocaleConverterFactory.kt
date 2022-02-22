package dragapult

import java.io.File
import java.util.*

interface DragapultLocaleConverterFactory {
    fun getInstance(): DragapultConverter<File, Set<Locale>>
}