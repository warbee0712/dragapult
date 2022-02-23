package dragapult.gradle.extension

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import java.io.File

abstract class DragapultExtension {

    @Nested
    abstract fun getArchive(): ArchiveProperties

    @Nested
    abstract fun getApp(): AppProperties

    fun archive(action: Action<in ArchiveProperties>) {
        action.execute(getArchive())
    }

    fun app(action: Action<in AppProperties>) {
        action.execute(getApp())
    }

}

fun ExtensionContainer.dragapultExtension(): DragapultExtension {
    return findByType(DragapultExtension::class.java)
        ?: create("dragapult", DragapultExtension::class.java)
}

abstract class ArchiveProperties {

    abstract fun getUrl(): Property<String>
    abstract fun getDownloadDir(): RegularFileProperty

}

abstract class AppProperties {

    abstract fun getFile(): RegularFileProperty
    abstract fun getType(): Property<String>

}

fun ArchiveProperties.getUrlDefault(): Property<String> {
    return getUrl()
        .convention("https://github.com/diareuse/dragapult/releases/latest/download/dragapult-app.zip")
}

fun ArchiveProperties.getDownloadDirDefault(project: Project): RegularFileProperty {
    val destination = File(project.buildDir, "tmp-dragapult")
    val directory = project.objects.fileProperty()
    destination.mkdirs()
    directory.set(destination)
    return getDownloadDir().convention(directory)
}
