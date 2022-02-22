package dragapult

import java.io.File

class PlatformFileDescriptorFileGuard(
    private val source: PlatformFileDescriptor
) : PlatformFileDescriptor by source {

    override fun getFile(directory: File): File {
        val file = source.getFile(directory)

        if (file.exists() && !file.isFile)
            file.deleteRecursively()

        if (!file.exists())
            file.createNewFile()

        return file
    }

}