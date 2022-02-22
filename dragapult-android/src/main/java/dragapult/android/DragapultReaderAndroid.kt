package dragapult.android

import dragapult.DragapultReader
import dragapult.model.Key
import dragapult.model.Localization
import dragapult.model.Value
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

class DragapultReaderAndroid(
    private val locale: Locale,
    private val file: File
) : DragapultReader<Localization> {

    override fun read(): Localization {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder() // <–- should be constructor argument
        val document = builder.parse(file).apply { normalizeDocument() }
        val values = mutableMapOf<Key, Value>()
        val elements = document.getElementsByTagName("string")
        for (element in elements) {
            val key = element.attributes.getNamedItem("name").textContent
            val value = element.textContent
            values[key] = value
        }
        return Localization(locale, values)
    }

}

operator fun NodeList.iterator(): Iterator<Node> {
    return iterator {
        for (i in 0 until length)
            yield(item(i))
    }
}