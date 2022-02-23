package dragapult.gradle.model

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

data class TaskRegistry(
    val project: Project,
    val tasks: Map<String, TaskProvider<out Task>>
) {

    fun append(task: TaskProvider<out Task>): TaskRegistry {
        val tasks = tasks.toMutableMap()
        tasks[task.name] = task
        return copy(tasks = tasks)
    }

}