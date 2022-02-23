package dragapult.gradle.task

import dragapult.gradle.extension.dragapult
import dragapult.gradle.extension.getOutputDefault
import org.gradle.api.Action
import org.gradle.api.Project
import com.android.build.gradle.internal.plugins.AppPlugin as AppPluginInternal
import com.android.build.gradle.internal.plugins.LibraryPlugin as LibraryPluginInternal

class RegisterSourceSetEffect : Action<Project> {

    override fun execute(project: Project) {
        val extension = project.extensions.dragapult()
        val resourcesDir = extension.getApp().getOutputDefault(project).get().asFile
        val plugin = getBasePluginOrThrow(project)
        plugin.extension.sourceSets.getByName("main") {
            it.res.setSrcDirs(it.res.srcDirs + resourcesDir.absolutePath)
        }
    }

    private fun getBasePluginOrThrow(project: Project) = with(project.plugins) {
        findPlugin(AppPluginInternal::class.java) ?: findPlugin(LibraryPluginInternal::class.java)
    }.let(::requireNotNull)

}