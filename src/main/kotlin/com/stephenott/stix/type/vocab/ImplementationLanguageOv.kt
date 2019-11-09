package com.stephenott.stix.type.vocab

class ImplementationLanguages(private val languages: LinkedHashSet<ImplementationLanguage> = linkedSetOf()) :
    Set<ImplementationLanguage> by languages {
}

class ImplementationLanguage(private val language: String) : OpenVocab {

    override fun getValue(): String {
        return language
    }

    companion object {

        const val vocabName = "implementation-language-ov"

        var vocab: LinkedHashSet<String> = linkedSetOf(
            "applescript", "bash", "c",
            "c++", "c#,", "go",
            "java", "javascript", "lua",
            "objective-c", "perl", "php",
            "powershell", "python", "ruby",
            "scala", "swift", "typescript",
            "visual-basic", "x86-32", "x86-64"
        )
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }

    init {
        require(this.language in vocab)
    }
}