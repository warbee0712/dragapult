package dragapult

import com.google.common.truth.Truth.assertThat
import dragapult.test.inputOf
import dragapult.test.nextString
import dragapult.test.outputOf
import dragapult.test.testExpression
import dragapult.tooling.converterInputPassing
import org.junit.jupiter.api.Test

class DragapultConverterConcatTest {

    @Test
    fun `uses all converters`() = testExpression<DragapultConverterConcat<String, String>> {
        inputs {
            val input = nextString()
            val converter = DragapultConverterConcat<String, String>(
                converterInputPassing(),
                converterInputPassing()
            )
            inputOf(converter, input)
        }
        outputs { (converter, input: String) ->
            outputOf(converter.convert(input), listOf(input, input))
        }
        test { (output: List<String>, expected: List<String>) ->
            assertThat(output).isEqualTo(expected)
        }
    }

    @Test
    fun `returns empty list on empty input`() = testExpression<DragapultConverterConcat<String, String>> {
        inputs {
            inputOf(DragapultConverterConcat(), nextString())
        }
        outputs { (converter, input: String) ->
            outputOf(converter.convert(input), emptyList<String>())
        }
        test { (output: List<String>, expected: List<String>) ->
            assertThat(output).isEqualTo(expected)
        }
    }

}



