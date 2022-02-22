package dragapult

class DragapultReaderConcat<Output>(
    private vararg val readers: DragapultReader<Output>
) : DragapultReader<List<Output>> {

    override fun read(): List<Output> {
        return readers.map { it.read() }
    }

}