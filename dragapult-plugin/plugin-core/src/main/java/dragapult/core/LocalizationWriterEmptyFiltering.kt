package dragapult.core

class LocalizationWriterEmptyFiltering(
    private val origin: LocalizationWriter
) : LocalizationWriter {

    override fun write(values: Sequence<Pair<Key, Value>>) {
        origin.write(values.filterNot { (_, value) -> value.isBlank() })
    }

}