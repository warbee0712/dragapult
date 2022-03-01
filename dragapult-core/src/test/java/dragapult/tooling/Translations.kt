package dragapult.tooling

import dragapult.model.LocalizationSet
import dragapult.model.Translations

fun translationsOf(vararg values: Pair<String, LocalizationSet>): Translations = mapOf(pairs = values)