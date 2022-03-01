package dragapult

import com.google.common.truth.Truth.assertThat
import dragapult.model.Localization
import dragapult.model.Translations
import dragapult.test.inputOf
import dragapult.test.outputOf
import dragapult.test.testExpression
import dragapult.tooling.localizationSetOf
import dragapult.tooling.translationsOf
import org.junit.jupiter.api.Test
import java.util.*

class DragapultConverterLocalizationTest {

    @Test
    fun `returns last value of locale`() = testExpression<DragapultConverterLocalization> {
        inputs {
            val locale = Locale.forLanguageTag("en")
            val translations = translationsOf(
                "" to localizationSetOf(
                    locale to "first",
                    locale to "last",
                )
            )
            val converter = DragapultConverterLocalization(locale)
            inputOf(converter, translations)
        }
        outputs { (converter, translations: Translations) ->
            outputOf(converter.convert(translations), "last")
        }
        test { (localization: Localization, expected: String) ->
            assertThat(localization.values[""]).isEqualTo(expected)
        }
    }

    @Test
    fun `returns all keys and values with locale`() = testExpression<DragapultConverterLocalization> {
        inputs {
            val locale = Locale.forLanguageTag("en")
            val converter = DragapultConverterLocalization(locale)
            val translations = translationsOf(
                "key" to localizationSetOf(locale to "value"),
                "key2" to localizationSetOf(Locale.forLanguageTag("cs") to "foo")
            )
            inputOf(converter, translations)
        }
        outputs { (converter, translations: Translations) ->
            outputOf(converter.convert(translations))
        }
        test { (output: Localization) ->
            assertThat(output.values.keys).hasSize(1)
            assertThat(output.values.keys).contains("key")
            assertThat(output.values.values).contains("value")
        }
    }

    @Test
    fun `skips keys without locale`() = testExpression<DragapultConverterLocalization> {
        inputs {
            val locale = Locale.forLanguageTag("en")
            val converter = DragapultConverterLocalization(locale)
            val translations = translationsOf(
                "key" to localizationSetOf(),
                "key2" to localizationSetOf()
            )
            inputOf(converter, translations)
        }
        outputs { (converter, translations: Translations) ->
            outputOf(converter.convert(translations))
        }
        test { (output: Localization) ->
            assertThat(output.values).isEmpty()
        }
    }

    @Test
    fun `returns identical locale`() = testExpression<DragapultConverterLocalization> {
        inputs {
            val locale = Locale.forLanguageTag("en")
            inputOf(DragapultConverterLocalization(locale), locale)
        }
        outputs { (converter, locale: Locale) ->
            outputOf(converter.convert(translationsOf()), locale)
        }
        test { (localization: Localization, locale: Locale) ->
            assertThat(localization.locale).isSameInstanceAs(locale)
        }
    }

}