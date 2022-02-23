package dragapult.gradle.chain

import dragapult.gradle.factory.TaskFactory
import dragapult.gradle.factory.TaskFactoryAsync

class DragapultTaskChainBuilderDefault : DragapultTaskChainBuilder {

    private val factories = mutableListOf<TaskFactoryAsync>()

    override fun append(factory: TaskFactory) = appendAsync { project, callback ->
        callback.onCreated(factory.getInstance(project))
    }

    override fun appendAsync(factory: TaskFactoryAsync) = apply {
        factories += factory
    }

    override fun build(): DragapultTaskChain {
        return DragapultTaskChainDefault(factories)
    }

}