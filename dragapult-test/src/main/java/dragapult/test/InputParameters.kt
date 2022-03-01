package dragapult.test

class InputParameters<Type>(
    private val source: Type,

    @PublishedApi
    internal val parameters: Array<out Any>
) {

    operator fun component1(): Type {
        return source
    }

    inline operator fun <reified T> component2(): T {
        return parameters[0] as T
    }

    inline operator fun <reified T> component3(): T {
        return parameters[1] as T
    }

    inline operator fun <reified T> component4(): T {
        return parameters[2] as T
    }

    inline operator fun <reified T> component5(): T {
        return parameters[3] as T
    }

}

fun <Type> inputOf(source: Type, vararg parameters: Any) = InputParameters(source, parameters = parameters)