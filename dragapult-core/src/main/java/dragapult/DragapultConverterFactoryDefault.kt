package dragapult

import dragapult.model.Localization
import dragapult.model.Translations
import java.util.*

class DragapultConverterFactoryDefault(
    private val converter: DragapultConverter<Translations, Set<Locale>>
) : DragapultConverterFactory {

    override fun getLocalization(translations: Translations): DragapultConverter<Translations, List<Localization>> {
        val locales = converter.convert(translations)
        val converters = locales.map { DragapultConverterLocalization(it) }.toTypedArray()
        return DragapultConverterConcat(converters = converters)
    }

}