package dragapult

import java.io.File

interface PlatformFileDescriptor {

    fun getHeader(): String
    fun getFooter(): String
    fun getLineBlueprint(): String
    fun getLineSeparator(isLast: Boolean): String
    fun getFile(directory: File): File

}