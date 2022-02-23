package dragapult.gradle.factory

import dragapult.gradle.callback.OnTaskCreated
import dragapult.gradle.model.TaskRegistry

fun interface TaskFactoryAsync {

    fun getInstanceAsync(registry: TaskRegistry, callback: OnTaskCreated)

}