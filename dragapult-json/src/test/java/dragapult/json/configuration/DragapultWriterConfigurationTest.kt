package dragapult.json.configuration

import com.google.common.truth.Truth.assertThat
import org.json.JSONObject
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
        file = File("/tmp/config.json")
        file.createNewFile()
        writer = DragapultWriterConfiguration(file)
    }

    @AfterEach
    fun tearDown() {
        file.deleteRecursively()
    }

    // ---

    @Test
    fun `writes key`() {
        val translations = mapOf("my-key" to mapOf(Locale.forLanguageTag("en") to "value"))
        writer.write(translations)
        val output = JSONObject(file.readText())
        assertThat(output.getJSONObject("my-key")).isNotNull()
    }

    @Test
    fun `writes translation values`() {
        val translations = mapOf("my-key" to mapOf(Locale.forLanguageTag("en") to "value"))
        writer.write(translations)
        val output = JSONObject(file.readText()).getJSONObject("my-key")
        assertThat(output.getString("en")).isEqualTo("value")
    }

}