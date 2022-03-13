package dragapult

import dragapult.model.Key
import dragapult.model.Localization
import dragapult.model.Value
import java.io.File

class DragapultWriterLocalization(
    private val directory: File,
    private val factory: PlatformFileDescriptorFactory,
    private val transformer: DragapultConverter<String, String>
) : DragapultWriter<Localization> {

    override fun write(input: Localization) {
        val descriptor = factory.getInstance(input.locale)
        val blueprint = descriptor.getLineBlueprint()
        descriptor.getFile(directory).writeLines(descriptor, input.values) { (key, value) ->
            blueprint.format(key, transformer.convert(value))
        }
    }

    private inline fun File.writeLines(
        descriptor: PlatformFileDescriptor,
        values: Map<Key, Value>,
        body: File.(Map.Entry<Key, Value>) -> String
    ) {
        writeText(descriptor.getHeader())
        val entries = values.entries
        appendText(descriptor.getLineSeparator(entries.isEmpty()))
        entries.forEachIndexed { index, entry ->
            appendText(body(entry))
            appendText(descriptor.getLineSeparator(index == entries.size - 1))
        }
        appendText(descriptor.getFooter())
    }

}