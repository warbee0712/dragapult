package dragapult

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class DragapultWriterConcatTest {

    private lateinit var writer: DragapultWriterConcat<Any>

    @Mock
    private lateinit var second: DragapultWriter<Any>

    @Mock
    private lateinit var first: DragapultWriter<Any>

    @BeforeEach
    fun prepare() {
        MockitoAnnotations.openMocks(this).close()
        writer = DragapultWriterConcat(first, second)
    }

    @Suppress("RedundantUnitExpression")
    @Test
    fun `writes all resources`() {
        val input = Any()
        var firstWritten = false
        var secondWritten = false
        whenever(first.write(input)).thenAnswer { firstWritten = true;Unit }
        whenever(second.write(input)).thenAnswer { secondWritten = true;Unit }
        writer.write(input)
        assertThat(firstWritten).isTrue()
        assertThat(secondWritten).isTrue()
    }

}