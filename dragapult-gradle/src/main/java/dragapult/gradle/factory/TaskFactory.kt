package dragapult.gradle.factory

import dragapult.gradle.model.TaskRegistry
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

fun interface TaskFactory {

    fun getInstance(registry: TaskRegistry): TaskProvider<out Task>

}