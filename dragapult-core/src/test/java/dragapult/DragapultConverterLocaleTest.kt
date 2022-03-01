package dragapult

import com.google.common.truth.Truth.assertThat
import dragapult.model.Translations
import dragapult.test.inputOf
import dragapult.test.outputOf
import dragapult.test.testExpression
import dragapult.tooling.localizationSetOf
import dragapult.tooling.translationsOf
import org.junit.jupiter.api.Test
import java.util.*

class DragapultConverterLocaleTest {

    @Test
    fun `returns set of locales found in input`() = testExpression<DragapultConverterLocale> {
        inputs {
            val converter = DragapultConverterLocale()
            val translations = translationsOf(
                "" to localizationSetOf(
                    Locale.forLanguageTag("en") to "",
                    Locale.forLanguageTag("cs") to ""
                )
            )
            inputOf(converter, translations)
        }
        outputs { (converter, translations: Translations) ->
            outputOf(converter.convert(translations))
        }
        test { (locales: Set<Locale>) ->
            assertThat(locales).hasSize(2)
            assertThat(locales).contains(Locale.forLanguageTag("en"))
            assertThat(locales).contains(Locale.forLanguageTag("cs"))
        }
    }

    @Test
    fun `returns empty set when input empty`() = testExpression<DragapultConverterLocale> {
        inputs {
            inputOf(DragapultConverterLocale(), translationsOf())
        }
        outputs { (converter, translations: Translations) ->
            outputOf(converter.convert(translations))
        }
        test { (locales: Set<Locale>) ->
            assertThat(locales).isEmpty()
        }
    }

    @Test
    fun `returns unique entries`() = testExpression<DragapultConverterLocale> {
        inputs {
            val converter = DragapultConverterLocale()
            val translations = translationsOf(
                "" to localizationSetOf(
                    Locale.forLanguageTag("en") to "",
                    Locale.forLanguageTag("en") to ""
                )
            )
            inputOf(converter, translations)
        }
        outputs { (converter, translations: Translations) ->
            outputOf(converter.convert(translations), 1, Locale.forLanguageTag("en"))
        }
        test { (output: Set<Locale>, count: Int, expected: Locale) ->
            assertThat(output).hasSize(count)
            assertThat(output).contains(expected)
        }
    }

}