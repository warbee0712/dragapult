package dragapult.android

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class DragapultConverterLocaleAndroidTest {

    private val fileName: String = "strings.xml"
    private lateinit var resDir: File
    private lateinit var converter: DragapultConverterLocaleAndroid

    @BeforeEach
    fun prepare() {
        converter = DragapultConverterLocaleAndroid(fileName = fileName)
        resDir = File("/tmp/dragapult-android")
    }

    @AfterEach
    fun tearDown() {
        resDir.deleteRecursively()
    }

    @Test
    fun `finds dirs with explicit language tag`() {
        var dir = File(resDir, "values-cs").apply { mkdirs() }
        File(dir, fileName).createNewFile()

        dir = File(resDir, "values-pl").apply { mkdirs() }
        File(dir, fileName).createNewFile()

        val locales = converter.convert(resDir)
        assertThat(locales).containsExactly(
            Locale.forLanguageTag("cs"),
            Locale.forLanguageTag("pl")
        )
    }

    @Test
    fun `finds default dir`() {
        val dir = File(resDir, "values").apply { mkdirs() }
        File(dir, fileName).createNewFile()

        val locales = converter.convert(resDir)
        assertThat(locales).containsExactly(Locale.forLanguageTag("en"))
    }

}