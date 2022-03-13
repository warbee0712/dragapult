package dragapult.android

import com.google.common.truth.Truth.assertThat
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

class DragapultReaderAndroidTest {

    private lateinit var locale: Locale
    private lateinit var reader: DragapultReaderAndroid
    private lateinit var file: File

    @BeforeEach
    fun prepare() {
        file = File("/tmp/android.xml")
        file.createNewFile()
        file.writeText(AndroidResource)
        locale = Locale.forLanguageTag("cs")
        reader = DragapultReaderAndroid(locale, file)
    }

    @AfterEach
    fun tearDown() {
        file.deleteRecursively()
    }

    @Test
    fun `reads file content`() {
        val output = reader.read()
        assertThat(output.values).containsExactly("key", "value")
    }

    @Test
    fun `passes locale`() {
        val output = reader.read()
        assertThat(output.locale).isSameInstanceAs(locale)
    }

}

@Language("xml")
private val AndroidResource = """<?xml version="1.0" encoding="utf-8"?>
<resources>
	<string name="key">value</string>
</resources>"""