package com.stephenott.stix.type

data class StixLang (val lang: String, val isDefinedValue: Boolean){
    init {
        //@TODO add RFC5646 language code validation
    }

    constructor(): this(DEFAULT_LANG, false)

    constructor(lang: String): this(lang, true)

    companion object{
        const val DEFAULT_LANG = "en"
    }
}