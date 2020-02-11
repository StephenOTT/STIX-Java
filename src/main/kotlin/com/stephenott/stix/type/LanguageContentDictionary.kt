package com.stephenott.stix.type

class LanguageContentDictionary(private val languages: Set<LanguageContent> = linkedSetOf()):
    Map<String, LanguageContent> by languages.associateBy({it.languageCode}){

    init {
        //@TODO

        //@TODO Add a JsonCreator constructor for the Map

    }
}

data class LanguageContent(val languageCode: String, val content: Map<String, Any>) {

    init {
        //@TODO add language code validation
        //@TODO add better type handler for the content Map
        //@TODO add support for untranslated content
    }
}