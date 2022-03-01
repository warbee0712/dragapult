package dragapult.test

@PublishedApi
internal class TestedExpressionImpl<Type> : TestedExpression<Type> {

    private lateinit var inputs: () -> InputParameters<Type>
    private lateinit var outputs: (InputParameters<Type>) -> OutputParameters
    private lateinit var test: (OutputParameters) -> Unit

    override fun inputs(body: () -> InputParameters<Type>) {
        this.inputs = body
    }

    override fun outputs(body: (InputParameters<Type>) -> OutputParameters) {
        this.outputs = body
    }

    override fun test(body: (OutputParameters) -> Unit) {
        this.test = body
    }

    @PublishedApi
    internal fun go() {
        val input = this.inputs.invoke()
        val output = this.outputs.invoke(input)
        this.test.invoke(output)
    }

}

inline fun <Type> testExpression(builder: TestedExpression<Type>.() -> Unit) {
    TestedExpressionImpl<Type>().apply(builder).go()
}