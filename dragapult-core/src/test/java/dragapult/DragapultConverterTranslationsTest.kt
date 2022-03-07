package dragapult

import com.google.common.truth.Truth.assertThat
import dragapult.model.Localization
import dragapult.model.Translations
import dragapult.test.inputOf
import dragapult.test.outputOf
import dragapult.test.testExpression
import org.junit.jupiter.api.Test
import java.util.*

class DragapultConverterTranslationsTest {

    @Test
    fun `returns all keys`() = testExpression<DragapultConverterTranslations> {
        inputs {
            inputOf(DragapultConverterTranslations())
        }
        outputs { (converter) ->
            val localizations = listOf(
                Localization(Locale.forLanguageTag("en"), mapOf("key" to "value")),
                Localization(Locale.forLanguageTag("en"), mapOf("key2" to "value2")),
                Localization(Locale.forLanguageTag("en"), mapOf("key3" to "value3"))
            )
            val expected = listOf("key", "key2", "key3")
            outputOf(converter.convert(localizations), expected)
        }
        test { (output: Translations, expected: List<String>) ->
            assertThat(output.keys).containsExactlyElementsIn(expected)
        }
    }

    @Test
    fun `returns last value for locale`() = testExpression<DragapultConverterTranslations> {
        inputs {
            inputOf(DragapultConverterTranslations())
        }
        outputs { (converter) ->
            val localizations = listOf(
                Localization(Locale.forLanguageTag("en"), mapOf("key" to "value")),
                Localization(Locale.forLanguageTag("en"), mapOf("key" to "value2"))
            )
            outputOf(converter.convert(localizations), "value2")
        }
        test { (output: Translations, expected: String) ->
            assertThat(output.getValue("key").getValue(Locale.forLanguageTag("en"))).isEqualTo(expected)
        }
    }

    @Test
    fun `returns all locales for key`() = testExpression<DragapultConverterTranslations> {
        inputs {
            inputOf(DragapultConverterTranslations())
        }
        outputs { (converter) ->
            val localizations = listOf(
                Localization(Locale.forLanguageTag("en"), mapOf("key" to "value")),
                Localization(Locale.forLanguageTag("cs"), mapOf("key" to "value-cs"))
            )
            val expected = listOf(
                Locale.forLanguageTag("en"),
                Locale.forLanguageTag("cs")
            )
            outputOf(converter.convert(localizations), expected)
        }
        test { (output: Translations, expected: List<Locale>) ->
            assertThat(output.getValue("key").keys).containsExactlyElementsIn(expected)
        }
    }

    @Test
    fun `returns values matching locale and key`() = testExpression<DragapultConverterTranslations> {
        inputs {
            inputOf(DragapultConverterTranslations())
        }
        outputs { (converter) ->
            val localizations = listOf(
                Localization(Locale.forLanguageTag("en"), mapOf("key" to "value")),
                Localization(Locale.forLanguageTag("cs"), mapOf("key" to "value-cs"))
            )
            outputOf(converter.convert(localizations))
        }
        test { (output: Translations) ->
            val key = output.getValue("key")
            assertThat(key.getValue(Locale.forLanguageTag("en"))).isEqualTo("value")
            assertThat(key.getValue(Locale.forLanguageTag("cs"))).isEqualTo("value-cs")
        }
    }

}