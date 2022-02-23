package dragapult.gradle.extension

import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import java.io.File

abstract class ArchiveProperties {

    abstract fun getUrl(): Property<String>
    abstract fun getDownloadDir(): RegularFileProperty

}

fun ArchiveProperties.getUrlDefault(): Property<String> {
    return getUrl()
        .convention("https://github.com/diareuse/dragapult/releases/latest/download/dragapult-app.zip")
}

fun ArchiveProperties.getDownloadDirDefault(project: Project): RegularFileProperty {
    val destination = File(project.buildDir, "dragapult")
    val directory = project.objects.fileProperty()
    destination.mkdirs()
    directory.set(destination)
    return getDownloadDir().convention(directory)
}
