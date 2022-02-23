package dragapult.gradle.factory

import dragapult.gradle.callback.OnTaskCreated
import org.gradle.api.Project

class TaskFactoryAsyncWrapper(
    private val factory: TaskFactory
) : TaskFactoryAsync {

    override fun getInstanceAsync(project: Project, callback: OnTaskCreated) {
        val instance = factory.getInstance(project)
        callback.onCreated(instance)
    }

}