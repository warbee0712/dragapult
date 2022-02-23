package dragapult.gradle.chain

import dragapult.gradle.factory.TaskFactoryAsync
import dragapult.gradle.model.TaskRegistry
import org.gradle.api.Project

class DragapultTaskChainDefault(
    factories: List<TaskFactoryAsync>
) : DragapultTaskChain {

    private val factories = ArrayDeque(factories)

    override fun register(project: Project) {
        val registry = TaskRegistry(project, emptyMap())
        register(registry)
    }

    private fun register(registry: TaskRegistry) {
        val factory = factories.removeFirstOrNull() ?: return
        factory.getInstanceAsync(registry) {
            register(registry.append(it))
        }
    }

}