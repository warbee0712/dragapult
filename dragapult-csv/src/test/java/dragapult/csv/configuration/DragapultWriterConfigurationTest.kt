package dragapult.csv.configuration

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class DragapultWriterConfigurationTest {

    private lateinit var writer: DragapultWriterConfiguration
    private lateinit var file: File

    @BeforeEach
    fun prepare() {
        file = File("/tmp/config.csv")
        file.createNewFile()
        writer = DragapultWriterConfiguration(file)
    }

    @AfterEach
    fun tearDown() {
        file.deleteRecursively()
    }

    // ---

    @Test
    fun `writes headers`() {
        val translations = mapOf("my-key" to mapOf(Locale.forLanguageTag("en") to "value"))
        writer.write(translations)
        val output = file.readLines().first()
        assertThat(output).contains("key")
        assertThat(output).contains("en")
    }

    @Test
    fun `writes translation values`() {
        val translations = mapOf("my-key" to mapOf(Locale.forLanguageTag("en") to "value"))
        writer.write(translations)
        val output = file.readLines()[1]
        assertThat(output).contains("my-key")
        assertThat(output).contains("value")
    }

}