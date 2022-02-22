package dragapult.app

import dragapult.*
import dragapult.app.factory.DragapultLocaleConverterFactoryDefault
import dragapult.app.factory.DragapultReaderFactoryDefault
import dragapult.app.factory.DragapultWriterFactoryConfigurationOutput
import dragapult.app.model.Platform
import dragapult.app.model.Source
import dragapult.app.options.ConsumeOptions
import dragapult.json.configuration.DragapultWriterJsonFormatter
import dragapult.model.Localization
import dragapult.model.Translations
import java.io.File
import dragapult.csv.configuration.DragapultWriterConfiguration as DragapultWriterConfigurationCsv
import dragapult.json.configuration.DragapultWriterConfiguration as DragapultWriterConfigurationJson

fun import(options: ConsumeOptions) {
    DragapultImporter.Builder()
        .setReader(getReader2(options.inputDirectory, options.inputType))
        .setConverter(getConverter2())
        .setWriter(getWriter2(options.outputFile, options.outputType))
        .build()
        .run()
}

private fun getWriter2(output: File, type: Source): DragapultWriter<Translations> {
    val json = DragapultWriterConcat(
        DragapultWriterConfigurationJson(output),
        DragapultWriterJsonFormatter(output)
    )
    val csv = DragapultWriterConfigurationCsv(output)
    val factory = DragapultWriterFactoryConfigurationOutput(
        json = json,
        csv = csv
    )
    return factory.getInstance(type)
}

private fun getConverter2(): DragapultConverter<List<Localization>, Translations> {
    return DragapultConverterTranslations()
}

private fun getReader2(directory: File, platform: Platform): DragapultReader<List<Localization>> {
    val converter = DragapultLocaleConverterFactoryDefault(platform).getInstance()
    val factory = DragapultReaderFactoryDefault(platform, converter, getDescriptorFactory(platform))
    val readers = factory.getInstances(directory).toTypedArray()
    return DragapultReaderConcat(readers = readers)
}