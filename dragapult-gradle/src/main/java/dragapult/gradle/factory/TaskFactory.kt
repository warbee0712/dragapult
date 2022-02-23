package dragapult.gradle.factory

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

fun interface TaskFactory {

    fun getInstance(project: Project): TaskProvider<out Task>

}