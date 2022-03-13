package dragapult.android

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class PlatformFileDescriptorAndroidTest {

    private val defaultLanguage: String = "en"
    private val fileName: String = "strings.xml"
    private lateinit var resDir: File
    private lateinit var locale: Locale
    private lateinit var descriptor: PlatformFileDescriptorAndroid

    @BeforeEach
    fun prepare() {
        locale = Locale.forLanguageTag("cs")
        descriptor = PlatformFileDescriptorAndroid(locale, fileName, defaultLanguage)
        resDir = File("/tmp/dragapult")
    }

    @AfterEach
    fun tearDown() {
        resDir.deleteRecursively()
    }

    // ---

    @Test
    fun `header contains resources tag`() {
        assertThat(descriptor.getHeader()).contains("<resources>")
    }

    @Test
    fun `header contains xml descriptor`() {
        assertThat(descriptor.getHeader()).contains("""<?xml version="1.0" encoding="utf-8"?>""")
    }

    @Test
    fun `footer contains resources tag`() {
        assertThat(descriptor.getFooter()).contains("</resources>")
    }

    @Test
    fun `line blueprint is string tag`() {
        assertThat(descriptor.getLineBlueprint()).containsMatch("<string.*>.*</string>")
    }

    @Test
    fun `line blueprint contains name template`() {
        assertThat(descriptor.getLineBlueprint()).containsMatch("name=\"%s\"")
    }

    @Test
    fun `line blueprint contains value template`() {
        assertThat(descriptor.getLineBlueprint()).containsMatch(">%s<")
    }

    @Test
    fun `line separator is system separator`() {
        assertThat(descriptor.getLineSeparator(true)).isEqualTo(System.lineSeparator())
        assertThat(descriptor.getLineSeparator(false)).isEqualTo(System.lineSeparator())
    }

    @Test
    fun `file contains language tag if not default`() {
        val file = descriptor.getFile(resDir)
        var parent = file.parentFile
        while (!parent.name.startsWith("values")) {
            parent = parent.parentFile
        }
        assertThat(parent.name).isEqualTo("values-${locale.language}")
    }

    @Test
    fun `file does not contain language tag if default`() {
        descriptor = PlatformFileDescriptorAndroid(Locale.forLanguageTag("en"), fileName, "en")
        val file = descriptor.getFile(resDir)
        var parent = file.parentFile
        while (!parent.name.startsWith("values")) {
            parent = parent.parentFile
        }
        assertThat(parent.name).isEqualTo("values")
    }

}