package dragapult

import dragapult.model.Key
import dragapult.model.Localization
import dragapult.model.Translations
import dragapult.model.Value
import java.util.*

class DragapultConverterLocalization(
    private val locale: Locale
) : DragapultConverter<Translations, Localization> {

    override fun convert(input: Translations): Localization {
        val output = mutableMapOf<Key, Value>()

        for ((key, localizations) in input)
            output[key] = localizations[locale] ?: continue

        return Localization(locale, output)
    }

}