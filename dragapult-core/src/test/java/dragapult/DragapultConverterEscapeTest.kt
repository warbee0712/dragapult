package dragapult

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DragapultConverterEscapeTest {

    private lateinit var converter: DragapultConverterEscape

    @BeforeEach
    fun prepare() {
        converter = DragapultConverterEscape()
    }

    @Test
    fun `escapes backslash`() {
        val output = converter.convert("\\")
        assertThat(output).isEqualTo("\\\\")
    }

    @Test
    fun `escapes double quotes`() {
        val output = converter.convert("\"")
        assertThat(output).isEqualTo("\\\"")
    }

    @Test
    fun `escapes single quotes`() {
        val output = converter.convert("\'")
        assertThat(output).isEqualTo("\\\'")
    }

    @Test
    fun `escapes backspace`() {
        val output = converter.convert("\b")
        assertThat(output).isEqualTo("\\b")
    }

    @Test
    fun `escapes new page`() {
        val output = converter.convert("\u000c")
        assertThat(output).isEqualTo("\\f")
    }

    @Test
    fun `escapes new line`() {
        val output = converter.convert("\n")
        assertThat(output).isEqualTo("\\n")
    }

    @Test
    fun `escapes carriage return`() {
        val output = converter.convert("\r")
        assertThat(output).isEqualTo("\\r")
    }

    @Test
    fun `escapes tab`() {
        val output = converter.convert("\t")
        assertThat(output).isEqualTo("\\t")
    }

}