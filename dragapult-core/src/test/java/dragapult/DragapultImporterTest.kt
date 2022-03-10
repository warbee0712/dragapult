package dragapult

import dragapult.model.Localization
import dragapult.model.Translations
import dragapult.test.TestSuccessful
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class DragapultImporterTest {

    @Mock
    private lateinit var writer: DragapultWriter<Translations>

    @Mock
    private lateinit var converter: DragapultConverter<List<Localization>, Translations>

    @Mock
    private lateinit var reader: DragapultReader<List<Localization>>

    private lateinit var importer: DragapultImporter

    @BeforeEach
    fun prepare() {
        MockitoAnnotations.openMocks(this).close()
        importer = DragapultImporter(reader, converter, writer)
    }

    @Test
    fun `calls reader`() {
        whenever(reader.read()).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            importer.run()
        }
    }

    @Test
    fun `calls converter`() {
        whenever(reader.read()).thenReturn(emptyList())
        whenever(converter.convert(emptyList())).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            importer.run()
        }
    }

    @Test
    fun `writes converter results`() {
        whenever(reader.read()).thenReturn(emptyList())
        whenever(converter.convert(emptyList())).thenReturn(emptyMap())
        whenever(writer.write(emptyMap())).thenThrow(TestSuccessful())
        assertThrows<TestSuccessful> {
            importer.run()
        }
    }

}