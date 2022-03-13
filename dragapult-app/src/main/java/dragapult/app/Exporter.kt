package dragapult.app

import dragapult.*
import dragapult.app.factory.DragapultReaderFactoryConfigurationInput
import dragapult.app.factory.PlatformFileDescriptorFactoryDefault
import dragapult.app.model.Platform
import dragapult.app.model.Source
import dragapult.app.options.GenerateOptions
import dragapult.json.configuration.DragapultReaderConfiguration
import dragapult.model.Localization
import dragapult.model.Translations
import java.io.File

fun export(options: GenerateOptions) {
    DragapultExporter(
        reader = getReader(options.inputFile, options.inputType),
        converter = getConverter(),
        writer = getWriter(options.outputDirectory, options.outputType)
    ).run()
}

fun getDescriptorFactory(platform: Platform): PlatformFileDescriptorFactory {
    return PlatformFileDescriptorFactoryDefault(platform)
}

private fun getWriter(directory: File, platform: Platform): DragapultWriter<Localization> {
    return DragapultWriterLocalization(directory, getDescriptorFactory(platform), getTransformer())
}

private fun getConverter(): DragapultConverterFactory {
    return DragapultConverterFactoryDefault(DragapultConverterLocale())
}

private fun getReader(input: File, type: Source): DragapultReader<Translations> {
    val json = DragapultReaderConfiguration(input)
    val csv = dragapult.csv.configuration.DragapultReaderConfiguration(input)
    val factory = DragapultReaderFactoryConfigurationInput(
        json = json,
        csv = csv
    )
    return factory.getInstance(type)
}

private fun getTransformer(): DragapultConverter<String, String> {
    return DragapultConverterEscape()
}