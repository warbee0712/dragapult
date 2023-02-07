package dragapult.android

import com.google.auto.service.AutoService
import dragapult.core.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class LocalizationWriterAndroid(
    private val file: File
) : LocalizationWriter {

    override fun write(values: Sequence<Pair<Key, Value>>) {
        val document = createDocument {
            createElement("resources") {
                for ((key, value) in values) appendElement("string") {
                    setAttribute("name", key)
                    textContent = value
                }
            }
        }

        document.write(file)
    }

    // ---

    private inline fun createDocument(body: Document.() -> Element): Document {
        val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val document = builder.newDocument()
        document.appendChild(document.run(body))
        return document
    }

    private inline fun Document.createElement(name: String, body: Element.() -> Unit): Element {
        return createElement(name).apply(body)
    }

    private inline fun Element.appendElement(name: String, body: Element.() -> Unit) {
        val element = ownerDocument.createElement(name).apply(body)
        appendChild(element)
    }

    // ---

    private fun Document.write(file: File) {
        val transformer = TransformerFactory.newInstance().newTransformer().apply {
            setOutputProperty(OutputKeys.INDENT, "yes")
        }
        val source = DOMSource(this)
        val result = StreamResult(file)
        transformer.transform(source, result)
    }

    // ---

    @AutoService(LocalizationWriter.Factory::class)
    class Factory : LocalizationWriter.Factory {

        override val type: LocalizationType
            get() = LocalizationTypeAndroid

        override fun create(file: File): LocalizationWriter {
            var writer: LocalizationWriter
            writer = LocalizationWriterAndroid(file)
            writer = LocalizationWriterReplacing(writer, "%@", "%s")
            writer = LocalizationWriterReplacing(writer, "\'", "\\'")
            writer = LocalizationWriterEmptyFiltering(writer)
            return writer
        }

    }

}