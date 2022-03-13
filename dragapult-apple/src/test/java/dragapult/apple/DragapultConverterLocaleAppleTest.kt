package dragapult.apple

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class DragapultConverterLocaleAppleTest {

    private val fileName: String = "Localizable.strings"
    private lateinit var resDir: File
    private lateinit var converter: DragapultConverterLocaleApple

    @BeforeEach
    fun prepare() {
        converter = DragapultConverterLocaleApple(fileName = fileName)
        resDir = File("/tmp/dragapult-apple")
    }

    @AfterEach
    fun tearDown() {
        resDir.deleteRecursively()
    }

    @Test
    fun `finds dirs with explicit language tag`() {
        var dir = File(resDir, "cs.lproj").apply { mkdirs() }
        File(dir, fileName).createNewFile()

        dir = File(resDir, "pl.lproj").apply { mkdirs() }
        File(dir, fileName).createNewFile()

        val locales = converter.convert(resDir)
        assertThat(locales).containsExactly(
            Locale.forLanguageTag("cs"),
            Locale.forLanguageTag("pl")
        )
    }

}