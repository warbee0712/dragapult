package dragapult

import com.google.common.truth.Truth.assertThat
import dragapult.model.Localization
import dragapult.test.nextString
import dragapult.tooling.localizationOf
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.io.File
import java.util.*

class DragapultWriterLocalizationTest {

    private lateinit var locale: Locale
    private lateinit var writer: DragapultWriterLocalization
    private lateinit var file: File

    @Mock
    private lateinit var transformer: DragapultConverter<String, String>

    @Mock
    private lateinit var factory: PlatformFileDescriptorFactory

    @Mock
    private lateinit var descriptor: PlatformFileDescriptor

    @BeforeEach
    fun prepare() {
        MockitoAnnotations.openMocks(this).close()
        file = File("/tmp/dragapult-writer-localization.txt")
        locale = Locale.getDefault()
        writer = DragapultWriterLocalization(file, factory, transformer)
    }

    @AfterEach
    fun tearDown() {
        file.writeText("")
        file.deleteRecursively()
    }

    // ---

    @Test
    fun `writes header`() {
        val expected = nextString()
        val localization = localizationOf(locale, "key" to "value").mockTransformer()
        mockDescriptor(header = expected)
        whenever(factory.getInstance(locale)).thenReturn(descriptor)
        writer.write(localization)
        assertThat(getLines().first()).isEqualTo(expected)
    }

    @Test
    fun `writes entry`() {
        val key = nextString()
        val value = nextString()
        val blueprint = "%s|%s"
        val localization = localizationOf(locale, key to value).mockTransformer()
        mockDescriptor(lineBlueprint = blueprint)
        whenever(factory.getInstance(locale)).thenReturn(descriptor)
        writer.write(localization)
        assertThat(getLines()).contains(blueprint.format(key, value))
    }

    @Test
    fun `writes separator`() {
        val separator = "!SEPARATOR!"
        val localization = localizationOf(locale, "key" to "value").mockTransformer()
        mockDescriptor(lineSeparator = separator)
        whenever(factory.getInstance(locale)).thenReturn(descriptor)
        writer.write(localization)
        assertThat(getText()).contains(separator)
    }

    @Test
    fun `writes last separator`() {
        val separator = "!SEPARATOR!"
        val localization = localizationOf(locale, "key" to "value").mockTransformer()
        mockDescriptor(lineSeparatorLast = separator)
        whenever(factory.getInstance(locale)).thenReturn(descriptor)
        writer.write(localization)
        assertThat(getText()).contains(separator)
    }

    @Test
    fun `writes footer`() {
        val expected = nextString()
        val localization = localizationOf(locale, "key" to "value").mockTransformer()
        mockDescriptor(footer = expected)
        whenever(factory.getInstance(locale)).thenReturn(descriptor)
        writer.write(localization)
        assertThat(getLines().last()).isEqualTo(expected)
    }

    // ---

    private fun mockDescriptor(
        lineBlueprint: String = "%s+%s",
        file: File = this.file,
        header: String = "<HEADER!>",
        lineSeparatorLast: String = System.lineSeparator(),
        lineSeparator: String = System.lineSeparator(),
        footer: String = "<!FOOTER>"
    ) {
        whenever(descriptor.getLineBlueprint()).thenReturn(lineBlueprint)
        whenever(descriptor.getFile(file)).thenReturn(file)
        whenever(descriptor.getHeader()).thenReturn(header)
        whenever(descriptor.getLineSeparator(true)).thenReturn(lineSeparatorLast)
        whenever(descriptor.getLineSeparator(false)).thenReturn(lineSeparator)
        whenever(descriptor.getFooter()).thenReturn((footer))
    }

    private fun Localization.mockTransformer() = apply {
        for ((_, value) in values) {
            whenever(transformer.convert(value)).thenReturn(value)
        }
    }

    private fun getLines() = file.readLines()
    private fun getText() = file.readText()

}