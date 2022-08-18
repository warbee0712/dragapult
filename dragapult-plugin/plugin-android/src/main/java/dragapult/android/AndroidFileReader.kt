package dragapult.android

import org.w3c.dom.Document
import org.w3c.dom.Node
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

class AndroidFileReader(
    private val file: File
) {

    fun read(): Sequence<Pair<String, String>> {
        return file
            .readDocument()
            .readElements("string")
            .map {
                val key = it.attributes.getNamedItem("name").textContent
                val value = it.textContent
                key to value
            }
    }

    private fun File.readDocument(): Document {
        val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        return builder.parse(this).apply { normalizeDocument() }
    }

    private fun Document.readElements(name: String): Sequence<Node> {
        val elements = getElementsByTagName(name)
        return sequence {
            for (i in 0 until elements.length) {
                yield(elements.item(i))
            }
        }
    }

    companion object {

        fun File.androidReader() = AndroidFileReader(this)

    }

}