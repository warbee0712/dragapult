package dragapult.core

import java.io.File
import java.io.IOException

abstract class ExternalLocalizationHolderWriteable : ExternalLocalizationHolder() {

    @Throws(IOException::class)
    abstract fun write(directory: File)

}