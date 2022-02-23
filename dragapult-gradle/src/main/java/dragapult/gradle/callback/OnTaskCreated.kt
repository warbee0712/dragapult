package dragapult.gradle.callback

import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

fun interface OnTaskCreated {

    fun onCreated(task: TaskProvider<out Task>)

}