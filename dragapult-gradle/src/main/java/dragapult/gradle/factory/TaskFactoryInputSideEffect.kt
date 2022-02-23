package dragapult.gradle.factory

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

class TaskFactoryInputSideEffect(
    private val source: TaskFactory,
    private val effect: Action<Project>
) : TaskFactory {

    override fun getInstance(project: Project): TaskProvider<out Task> {
        return source.getInstance(project).also {
            effect.execute(project)
        }
    }

}