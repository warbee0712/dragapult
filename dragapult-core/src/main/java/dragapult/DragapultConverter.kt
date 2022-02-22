package dragapult

interface DragapultConverter<Input, Output> {

    fun convert(input: Input): Output

}