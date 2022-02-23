package dragapult.gradle

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import dragapult.gradle.chain.taskChainBuilder
import dragapult.gradle.extension.dragapult
import dragapult.gradle.factory.*
import dragapult.gradle.task.DownloadBinaryTaskFactory
import dragapult.gradle.task.ExtractTaskFactory
import dragapult.gradle.task.GeneratorTaskFactory
import dragapult.gradle.task.RegisterSourceSetEffect
import org.gradle.api.Plugin
import org.gradle.api.Project

class DragapultPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.extensions.dragapult()
        val chain = taskChainBuilder()
            .append(DownloadBinaryTaskFactory())
            .append(ExtractTaskFactory())
            .appendAsync(wrapGenerator(getGenerator()))
            .build()

        chain.register(target)
    }

    private fun getGenerator(): TaskFactory {
        var source: TaskFactory
        source = GeneratorTaskFactory()
        source = TaskFactoryInputSideEffect(source, RegisterSourceSetEffect())
        return source
    }

    private fun wrapGenerator(source: TaskFactory): TaskFactoryAsync {
        val plugins = arrayOf(AppPlugin::class.java, LibraryPlugin::class.java)
        var factory: TaskFactoryAsync
        factory = TaskFactoryAsyncWrapper(source)
        factory = TaskFactoryAsyncSwitchingPlugin(
            hasPlugins = factory,
            hasNoPlugins = TaskFactoryAsyncDeferredPlugin(
                factory = factory,
                plugins = plugins
            ),
            plugins = plugins
        )
        return factory
    }

}
