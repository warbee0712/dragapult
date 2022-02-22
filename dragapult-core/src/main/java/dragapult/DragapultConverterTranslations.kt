package dragapult

import dragapult.model.Localization
import dragapult.model.Translations
import java.util.*

class DragapultConverterTranslations : DragapultConverter<List<Localization>, Translations> {

    override fun convert(input: List<Localization>): Translations {
        val categories = mutableMapOf<String, MutableMap<Locale, String>>()

        for (localization in input)
            for ((key, value) in localization.values) {
                val values = categories.getOrPut(key) { mutableMapOf() }
                values[localization.locale] = value
            }

        return categories
    }

}