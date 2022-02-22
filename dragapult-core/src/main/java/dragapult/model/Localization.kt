package dragapult.model

import java.util.*

data class Localization(
    val locale: Locale,
    val values: Map<Key, Value>
)