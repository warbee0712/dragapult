package dragapult.apple

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class PlatformFileDescriptorAppleTest {

    private val fileName: String = "Localizable.strings"
    private lateinit var resDir: File
    private lateinit var locale: Locale
    private lateinit var descriptor: PlatformFileDescriptorApple

    @BeforeEach
    fun prepare() {
        locale = Locale.forLanguageTag("cs")
        descriptor = PlatformFileDescriptorApple(locale, fileName)
        resDir = File("/tmp/dragapult")
    }

    @AfterEach
    fun tearDown() {
        resDir.deleteRecursively()
    }

    // ---

    @Test
    fun `header is blank`() {
        assertThat(descriptor.getHeader()).isEmpty()
    }

    @Test
    fun `footer is blank`() {
        assertThat(descriptor.getFooter()).isEmpty()
    }

    @Test
    fun `line blueprint is string tag`() {
        assertThat(descriptor.getLineBlueprint()).containsMatch("\".*\".*=.*\".*\";")
    }

    @Test
    fun `line blueprint contains name template`() {
        assertThat(descriptor.getLineBlueprint()).containsMatch("\"%s\".+")
    }

    @Test
    fun `line blueprint contains value template`() {
        assertThat(descriptor.getLineBlueprint()).containsMatch(".+\"%s\"")
    }

    @Test
    fun `line separator is system separator`() {
        assertThat(descriptor.getLineSeparator(true)).isEqualTo(System.lineSeparator())
        assertThat(descriptor.getLineSeparator(false)).isEqualTo(System.lineSeparator())
    }

    @Test
    fun `file contains language tag`() {
        val file = descriptor.getFile(resDir)
        var parent = file.parentFile
        while (!parent.name.endsWith(".lproj")) {
            parent = parent.parentFile
        }
        assertThat(parent.name).isEqualTo("${locale.language}.lproj")
    }

}