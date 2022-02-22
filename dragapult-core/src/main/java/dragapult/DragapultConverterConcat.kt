package dragapult

class DragapultConverterConcat<Input, Output>(
    private vararg val converters: DragapultConverter<Input, Output>
) : DragapultConverter<Input, List<Output>> {

    override fun convert(input: Input): List<Output> {
        return converters.map { it.convert(input) }
    }

}