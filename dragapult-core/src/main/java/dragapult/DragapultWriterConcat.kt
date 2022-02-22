package dragapult

class DragapultWriterConcat<Input>(
    private vararg val writers: DragapultWriter<Input>
) : DragapultWriter<Input> {

    override fun write(input: Input) {
        writers.forEach { it.write(input) }
    }

}