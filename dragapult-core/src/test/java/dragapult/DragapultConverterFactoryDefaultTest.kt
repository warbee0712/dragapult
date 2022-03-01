package dragapult

import com.google.common.truth.Truth.assertThat
import dragapult.model.Localization
import dragapult.model.Translations
import dragapult.test.inputOf
import dragapult.test.outputOf
import dragapult.test.testExpression
import dragapult.tooling.converterOf
import dragapult.tooling.translationsOf
import org.junit.jupiter.api.Test
import java.util.*

class DragapultConverterFactoryDefaultTest {

    @Test
    fun `returns instance of converter`() = testExpression<DragapultConverterFactoryDefault> {
        inputs {
            val input = translationsOf()
            val converter = converterOf<Translations, Set<Locale>> { emptySet() }
            inputOf(DragapultConverterFactoryDefault(converter), input)
        }
        outputs { (factory, input: Translations) ->
            outputOf(factory.getLocalization(input))
        }
        test { (converter: DragapultConverter<Translations, List<Localization>>) ->
            assertThat(converter).isNotNull()
            assertThat(converter).isInstanceOf(DragapultConverter::class.java)
        }
    }

    @Test
    fun `returns matching output count with locale set`() = testExpression<DragapultConverterFactoryDefault> {
        inputs {
            val input = translationsOf()
            val locales = setOf(Locale.getDefault())
            val converter = converterOf<Translations, Set<Locale>> { locales }
            inputOf(DragapultConverterFactoryDefault(converter), locales, input)
        }
        outputs { (factory, locales: Set<Locale>, input: Translations) ->
            val converter = factory.getLocalization(input)
            val output = converter.convert(input)
            outputOf(output, locales.size)
        }
        test { (output: List<Localization>, count: Int) ->
            assertThat(output).hasSize(count)
        }
    }

}