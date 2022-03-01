package dragapult.tooling

import dragapult.DragapultConverter

fun <I, O> converterOf(mapper: (I) -> O) = object : DragapultConverter<I, O> {
    override fun convert(input: I): O = mapper.invoke(input)
}

fun <I> converterInputPassing() = converterOf<I, I> { it }