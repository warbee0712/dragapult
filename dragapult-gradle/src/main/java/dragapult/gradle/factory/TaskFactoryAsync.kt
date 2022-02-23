package dragapult.gradle.factory

import dragapult.gradle.callback.OnTaskCreated
import org.gradle.api.Project

fun interface TaskFactoryAsync {

    fun getInstanceAsync(project: Project, callback: OnTaskCreated)

}