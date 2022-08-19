package dragapult.core

abstract class ExternalLocalizationHolder : Model {

    abstract val key: String
    abstract val values: Sequence<TranslationHolder>

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ExternalLocalizationHolder) return false

        if (key != other.key) return false
        if (values != other.values) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + values.hashCode()
        return result
    }

    companion object {

        operator fun invoke(
            key: String,
            values: Sequence<TranslationHolder>
        ): ExternalLocalizationHolder = ExternalLocalizationHolderDefault(
            key = key,
            values = values
        )

    }

}

private data class ExternalLocalizationHolderDefault(
    override val key: String,
    override val values: Sequence<TranslationHolder>
) : ExternalLocalizationHolder()

private data class ExternalLocalizationHolderMultiValue(
    override val key: String,
    private val holders: Sequence<ExternalLocalizationHolder>
) : ExternalLocalizationHolder() {

    constructor(
        key: String,
        vararg holders: ExternalLocalizationHolder
    ) : this(
        key = key,
        holders = holders.asSequence()
    )

    override val values: Sequence<TranslationHolder>
        get() = holders
            .filter { it.key == key }
            .flatMap { it.values }
            .distinctBy { it.locale }

}