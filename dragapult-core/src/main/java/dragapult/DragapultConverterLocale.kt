package dragapult

import dragapult.model.Translations
import java.util.*

class DragapultConverterLocale : DragapultConverter<Translations, Set<Locale>> {

    override fun convert(input: Translations): Set<Locale> {
        val output = mutableSetOf<Locale>()

        for (localizations in input.values)
            for (locale in localizations.keys)
                output.add(locale)

        return output
    }

}