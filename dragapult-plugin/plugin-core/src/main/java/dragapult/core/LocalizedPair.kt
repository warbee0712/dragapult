package dragapult.core

abstract class LocalizedPair : Model {

    abstract val key: String
    abstract val value: String

    companion object {

        operator fun invoke(
            key: String,
            value: String
        ): LocalizedPair = LocalizedPairDefault(
            key = key,
            value = value
        )

    }

}

private data class LocalizedPairDefault(
    override val key: String,
    override val value: String
) : LocalizedPair()