package dragapult.gradle.chain

import dragapult.gradle.factory.TaskFactory
import dragapult.gradle.factory.TaskFactoryAsync

interface DragapultTaskChainBuilder {

    fun append(factory: TaskFactory): DragapultTaskChainBuilder
    fun appendAsync(factory: TaskFactoryAsync): DragapultTaskChainBuilder
    fun build(): DragapultTaskChain

}

fun taskChainBuilder(): DragapultTaskChainBuilder =
    DragapultTaskChainBuilderDefault()
