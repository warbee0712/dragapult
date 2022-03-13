package dragapult.csv.configuration

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class DragapultReaderConfigurationTest {

    private lateinit var file: File
    private lateinit var reader: DragapultReaderConfiguration

    @BeforeEach
    fun prepare() {
        file = File("/tmp/config.csv")
        file.writeText(CsvResource)
        reader = DragapultReaderConfiguration(file)
    }

    @AfterEach
    fun tearDown() {
        file.deleteRecursively()
    }

    // ---

    @Test
    fun `reads all keys`() {
        val output = reader.read()
        assertThat(output.keys).containsExactly("key", "key2")
    }

    @Test
    fun `reads all locales for a key`() {
        val output = reader.read()
        val key = output.getValue("key")
        assertThat(key.keys).containsExactly(
            Locale.forLanguageTag("en"),
            Locale.forLanguageTag("cs")
        )
    }

    @Test
    fun `reads value for a locale of a key`() {
        val output = reader.read()
        val key = output.getValue("key")
        assertThat(key.getValue(Locale.forLanguageTag("en"))).isEqualTo("value")
    }

}

private const val CsvResource = """
"key","en","cs"
"key","value","hodnota"
"key2","value","hodnota"
"""