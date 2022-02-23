package dragapult.gradle.chain

import dragapult.gradle.factory.TaskFactoryAsync
import org.gradle.api.Project

class DragapultTaskChainDefault(
    factories: List<TaskFactoryAsync>
) : DragapultTaskChain {

    private val factories = ArrayDeque(factories)

    override fun register(project: Project) {
        val factory = factories.removeFirstOrNull() ?: return
        factory.getInstanceAsync(project) {
            register(project)
        }
    }

}