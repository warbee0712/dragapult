package dragapult.test

class OutputParameters(
    @PublishedApi
    internal val parameters: Array<out Any>
) {

    inline operator fun <reified T> component1(): T {
        return parameters[0] as T
    }

    inline operator fun <reified T> component2(): T {
        return parameters[1] as T
    }

    inline operator fun <reified T> component3(): T {
        return parameters[2] as T
    }

    inline operator fun <reified T> component4(): T {
        return parameters[3] as T
    }

    inline operator fun <reified T> component5(): T {
        return parameters[4] as T
    }

}

fun outputOf(vararg parameters: Any) = OutputParameters(parameters = parameters)