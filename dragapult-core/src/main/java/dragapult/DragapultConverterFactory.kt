package dragapult

import dragapult.model.Localization
import dragapult.model.Translations

interface DragapultConverterFactory {

    fun getLocalization(translations: Translations): DragapultConverter<Translations, List<Localization>>

}