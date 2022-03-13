package dragapult.json

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class PlatformFileDescriptorJsonTest {

    private lateinit var resDir: File
    private lateinit var locale: Locale
    private lateinit var descriptor: PlatformFileDescriptorJson

    @BeforeEach
    fun prepare() {
        locale = Locale.forLanguageTag("cs")
        descriptor = PlatformFileDescriptorJson(locale)
        resDir = File("/tmp/dragapult")
    }

    @AfterEach
    fun tearDown() {
        resDir.deleteRecursively()
    }

    // ---

    @Test
    fun `header is blank`() {
        assertThat(descriptor.getHeader()).isEqualTo("{")
    }

    @Test
    fun `footer is blank`() {
        assertThat(descriptor.getFooter()).isEqualTo("}")
    }

    @Test
    fun `line blueprint is string tag`() {
        assertThat(descriptor.getLineBlueprint()).containsMatch("\".*\".*:.*\".*\"")
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
    fun `line separator has system separator and divider`() {
        assertThat(descriptor.getLineSeparator(false)).isEqualTo("," + System.lineSeparator())
    }

    @Test
    fun `line separator last is system separator`() {
        assertThat(descriptor.getLineSeparator(true)).isEqualTo(System.lineSeparator())
    }

    @Test
    fun `file contains language tag`() {
        val file = descriptor.getFile(resDir)
        assertThat(file.name).isEqualTo("${locale.language}.json")
    }

}