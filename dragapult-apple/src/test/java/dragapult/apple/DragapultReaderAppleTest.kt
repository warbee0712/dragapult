package dragapult.apple

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class DragapultReaderAppleTest {

    private lateinit var locale: Locale
    private lateinit var reader: DragapultReaderApple
    private lateinit var file: File

    @BeforeEach
    fun prepare() {
        file = File("/tmp/Localizable.strings")
        file.createNewFile()
        file.writeText(AppleResource)
        locale = Locale.forLanguageTag("cs")
        reader = DragapultReaderApple(locale, file)
    }

    @AfterEach
    fun tearDown() {
        file.deleteRecursively()
    }

    @Test
    fun `reads file content`() {
        val output = reader.read()
        assertThat(output.values).containsExactly(
            "key", "value",
            "key2", "value2",
            "key3", "value3",
            "key4", "value4"
        )
    }

    @Test
    fun `passes locale`() {
        val output = reader.read()
        assertThat(output.locale).isSameInstanceAs(locale)
    }

}

private const val AppleResource = """
"key" = "value";
"key2"="value2";
"key3" ="value3";
"key4"= "value4";
"""