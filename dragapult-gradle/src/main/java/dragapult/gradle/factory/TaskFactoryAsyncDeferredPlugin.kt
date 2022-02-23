package dragapult.gradle.factory

import dragapult.gradle.callback.OnTaskCreated
import org.gradle.api.Plugin
import org.gradle.api.Project

class TaskFactoryAsyncDeferredPlugin(
    private val factory: TaskFactoryAsync,
    private vararg val plugins: Class<out Plugin<*>>
) : TaskFactoryAsync {

    override fun getInstanceAsync(project: Project, callback: OnTaskCreated) {
        project.plugins
            .matching { it::class.java in plugins }
            .whenPluginAdded { factory.getInstanceAsync(project, callback) }
    }

}