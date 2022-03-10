package dragapult

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class DragapultReaderConcatTest {

    private lateinit var reader: DragapultReaderConcat<Any>

    @Mock
    private lateinit var second: DragapultReader<Any>

    @Mock
    private lateinit var first: DragapultReader<Any>

    @BeforeEach
    fun prepare() {
        MockitoAnnotations.openMocks(this).close()
        reader = DragapultReaderConcat(first, second)
    }

    @Test
    fun `returns output of all readers`() {
        whenever(first.read()).thenReturn(1)
        whenever(second.read()).thenReturn("2")
        val output = reader.read()
        assertThat(output).containsExactly(1, "2")
    }

}