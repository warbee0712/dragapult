package dragapult.json

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class DragapultConverterLocaleJsonTest {

    private lateinit var resDir: File
    private lateinit var converter: DragapultConverterLocaleJson

    @BeforeEach
    fun prepare() {
        converter = DragapultConverterLocaleJson()
        resDir = File("/tmp/dragapult-web")
        resDir.mkdirs()
    }

    @AfterEach
    fun tearDown() {
        resDir.deleteRecursively()
    }

    @Test
    fun `finds dirs with explicit language tag`() {
        File(resDir, "cs.json").createNewFile()
        File(resDir, "pl.json").createNewFile()

        val locales = converter.convert(resDir)
        assertThat(locales).containsExactly(
            Locale.forLanguageTag("cs"),
            Locale.forLanguageTag("pl")
        )
    }

}