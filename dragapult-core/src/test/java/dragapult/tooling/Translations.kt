package dragapult.tooling

import dragapult.model.*
import java.util.*

fun translationsOf(vararg values: Pair<String, LocalizationSet>): Translations = mapOf(pairs = values)
fun localizationSetOf(vararg values: Pair<Locale, String>): LocalizationSet = mapOf(pairs = values)
fun localizationOf(locale: Locale, vararg values: Pair<Key, Value>): Localization = Localization(locale, mapOf(*values))