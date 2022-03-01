package dragapult.tooling

import dragapult.model.LocalizationSet
import dragapult.model.Translations
import java.util.*

fun translationsOf(vararg values: Pair<String, LocalizationSet>): Translations = mapOf(pairs = values)
fun localizationSetOf(vararg values: Pair<Locale, String>): LocalizationSet = mapOf(pairs = values)