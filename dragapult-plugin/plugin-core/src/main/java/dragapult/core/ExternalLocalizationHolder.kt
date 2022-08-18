package dragapult.core

abstract class ExternalLocalizationHolder : Model {

    abstract val key: String
    abstract val values: Sequence<TranslationHolder>

    val locales
        get() = values.map { it.locale }.distinct()

    companion object {

        operator fun invoke(
            key: String,
            values: Iterable<TranslationHolder>
        ): ExternalLocalizationHolder = ExternalLocalizationHolderDefault(
            key = key,
            values = values.asSequence()
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