package dragapult.gradle.extension

import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import java.io.File

abstract class AppProperties {

    abstract fun getFile(): RegularFileProperty
    abstract fun getType(): Property<String>
    abstract fun getOutput(): RegularFileProperty

}

fun AppProperties.getOutputDefault(project: Project): RegularFileProperty {
    val default = File(project.buildDir, "generated/res/dragapult")
    val property = project.objects.fileProperty()
    property.set(default)
    return getOutput().convention(property)
}
