package dragapult.core

abstract class LocalizedPair : Model {

    abstract val key: String
    abstract val value: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LocalizedPair) return false

        if (key != other.key) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }

    companion object {

        operator fun invoke(
            key: String,
            value: String
        ): LocalizedPair {
            var pair: LocalizedPair
            pair = LocalizedPairDefault(key, value)
            pair = LocalizedPairReplacing(pair, "\n", "\\n")
            pair = LocalizedPairReplacing(pair, "...", "…")
            return pair
        }

    }

}

private data class LocalizedPairDefault(
    override val key: String,
    override val value: String
) : LocalizedPair()

private class LocalizedPairReplacing(
    private val origin: LocalizedPair,
    private val original: String,
    private val replacement: String
) : LocalizedPair() {

    override val key: String
        get() = origin.key

    override val value: String
        get() = origin.value.replace(original, replacement)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LocalizedPairReplacing) return false
        if (!super.equals(other)) return false

        if (origin != other.origin) return false
        if (original != other.original) return false
        if (replacement != other.replacement) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + origin.hashCode()
        result = 31 * result + original.hashCode()
        result = 31 * result + replacement.hashCode()
        return result
    }

}