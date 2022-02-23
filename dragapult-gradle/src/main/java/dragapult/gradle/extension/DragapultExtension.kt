package dragapult.gradle.extension

import org.gradle.api.Action
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.tasks.Nested

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

fun ExtensionContainer.dragapult(): DragapultExtension {
    return findByType(DragapultExtension::class.java)
        ?: create("dragapult", DragapultExtension::class.java)
}