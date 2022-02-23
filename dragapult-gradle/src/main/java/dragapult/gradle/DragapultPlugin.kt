package dragapult.gradle

import dragapult.gradle.chain.taskChainBuilder
import dragapult.gradle.extension.dragapultExtension
import dragapult.gradle.task.DownloadBinaryTaskFactory
import dragapult.gradle.task.ExtractTaskFactory
import dragapult.gradle.task.GeneratorTaskFactory
import dragapult.gradle.task.GeneratorTaskFactoryAsync
import org.gradle.api.Plugin
import org.gradle.api.Project

class DragapultPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.extensions.dragapultExtension()
        val chain = taskChainBuilder()
            .append(DownloadBinaryTaskFactory())
            .append(ExtractTaskFactory())
            .appendAsync(GeneratorTaskFactoryAsync(GeneratorTaskFactory()))
            .build()

        chain.register(target)
    }

}
