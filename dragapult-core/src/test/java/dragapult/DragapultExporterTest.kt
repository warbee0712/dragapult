package dragapult

import dragapult.model.Localization
import dragapult.model.Translations
import dragapult.test.TestSuccessful
import dragapult.tooling.localizationOf
import dragapult.tooling.localizationSetOf
import dragapult.tooling.translationsOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.util.*

class DragapultExporterTest {

    private lateinit var exporter: DragapultExporter

    @Mock
    private lateinit var writer: DragapultWriter<Localization>

    @Mock
    private lateinit var factory: DragapultConverterFactory

    @Mock
    private lateinit var reader: DragapultReader<Translations>

    @Mock
    private lateinit var converter: DragapultConverter<Translations, List<Localization>>

    @BeforeEach
    fun prepare() {
        MockitoAnnotations.openMocks(this).close()
        exporter = DragapultExporter(reader, factory, writer)
    }

    @Test
    fun `reads translations`() {
        whenever(reader.read()).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            exporter.run()
        }
    }

    @Test
    fun `writes localization`() {
        val translations = translationsOf(
            "key" to localizationSetOf(
                Locale.forLanguageTag("en") to "value-en",
                Locale.forLanguageTag("cs") to "value-cs"
            )
        )
        val localizations = listOf(
            localizationOf(Locale.getDefault())
        )
        whenever(reader.read()).thenReturn(translations)
        whenever(factory.getLocalization(translations)).thenReturn(converter)
        whenever(converter.convert(translations)).thenReturn(localizations)
        whenever(writer.write(localizations.first())).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            exporter.run()
        }
    }

}