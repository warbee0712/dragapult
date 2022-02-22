package dragapult.model

typealias Translations = Map<String, LocalizationSet>

val Translations.languages
    get() = values
        .asSequence()
        .flatMap { it.keys }
        .distinct()