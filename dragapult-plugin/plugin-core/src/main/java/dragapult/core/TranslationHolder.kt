package dragapult.core

import java.util.*

abstract class TranslationHolder : Model {

    abstract val locale: Locale
    abstract val value: String

    companion object {

        operator fun invoke(
            locale: Locale,
            value: String
        ): TranslationHolder = TranslationHolderDefault(
            locale = locale,
            value = value
        )

    }

}

private data class TranslationHolderDefault(
    override val locale: Locale,
    override val value: String
) : TranslationHolder()