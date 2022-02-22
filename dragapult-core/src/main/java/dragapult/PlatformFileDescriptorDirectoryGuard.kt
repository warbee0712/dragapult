package dragapult

import java.io.File

class PlatformFileDescriptorDirectoryGuard(
    private val source: PlatformFileDescriptor
) : PlatformFileDescriptor by source {

    override fun getFile(directory: File): File {
        directory.requireDirectory()
        return source.getFile(directory).also {
            it.parentFile.requireDirectory()
        }
    }

    private fun File.requireDirectory() {
        if (exists() && !isDirectory)
            deleteRecursively()

        if (!exists())
            mkdirs()
    }

}