package dragapult.test

import kotlin.random.Random

val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9') + listOf(' ', '-', '_')

fun nextString(length: Int = Random.nextInt(1, 30)) = sequence {
    for (i in 0 until length)
        yield(charPool.random())
}.joinToString(separator = "")