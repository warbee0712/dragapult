package dragapult.gradle.factory

import dragapult.gradle.callback.OnTaskCreated
import org.gradle.api.Plugin
import org.gradle.api.Project

class TaskFactoryAsyncSwitchingPlugin(
    private val hasPlugins: TaskFactoryAsync,
    private val hasNoPlugins: TaskFactoryAsync,
    private vararg val plugins: Class<out Plugin<*>>
) : TaskFactoryAsync {

    override fun getInstanceAsync(project: Project, callback: OnTaskCreated) {
        val container = project.plugins
        if (plugins.any { container.hasPlugin(it) }) {
            hasPlugins.getInstanceAsync(project, callback)
        } else {
            hasNoPlugins.getInstanceAsync(project, callback)
        }
    }

}