package dragapult.json.factory

import com.google.auto.service.AutoService
import dragapult.core.LocalizationType
import dragapult.core.PlatformLocalizedFile
import dragapult.core.PlatformLocalizedFileWriteable
import dragapult.json.LocalizationTypeJson
import dragapult.json.adapter.PlatformLocalizedFileWriteableJsonAdapter

@AutoService(PlatformLocalizedFileWriteable.Factory::class)
class PlatformLocalizedFileWriteableFactoryJson : PlatformLocalizedFileWriteable.Factory {

    override val type: LocalizationType
        get() = LocalizationTypeJson

    override fun writeable(file: PlatformLocalizedFile): PlatformLocalizedFileWriteable {
        return PlatformLocalizedFileWriteableJsonAdapter(file)
    }

}