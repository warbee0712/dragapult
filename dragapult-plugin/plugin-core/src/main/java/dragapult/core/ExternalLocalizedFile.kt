package dragapult.core

import java.util.*

abstract class ExternalLocalizedFile : Model {

    abstract val locale: Locale
    abstract val values: Sequence<LocalizedPair>

    val keys
        get() = values.map { it.key }.distinct()

    companion object {

        operator fun invoke(
            locale: Locale,
            values: Iterable<LocalizedPair>
        ): ExternalLocalizedFile = ExternalLocalizedFileDefault(
            locale = locale,
            values = values.asSequence()
        )

    }

}

private data class ExternalLocalizedFileDefault(
    override val locale: Locale,
    override val values: Sequence<LocalizedPair>
) : ExternalLocalizedFile()