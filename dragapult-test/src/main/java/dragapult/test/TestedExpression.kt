package dragapult.test

@TestedMarker
interface TestedExpression<Type> {

    fun inputs(body: () -> InputParameters<Type>)
    fun outputs(body: (InputParameters<Type>) -> OutputParameters)
    fun test(body: (OutputParameters) -> Unit)

}