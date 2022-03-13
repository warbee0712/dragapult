package dragapult.json

import com.google.common.truth.Truth.assertThat
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class DragapultReaderJsonTest {

    private lateinit var locale: Locale
    private lateinit var reader: DragapultReaderJson
    private lateinit var file: File

    @BeforeEach
    fun prepare() {
        file = File("/tmp/cs.json")
        file.createNewFile()
        file.writeText(JsonResource)
        locale = Locale.forLanguageTag("cs")
        reader = DragapultReaderJson(locale, file)
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

@Language("json")
private const val JsonResource = """{
  "key" : "value",
    "key2":"value2",
      "key3" :"value3",
        "key4": "value4"
}"""