package com.stephenott.stix.type.vocab

class IdentityClass(private val identityClass: String): OpenVocab {

    override fun getValue(): String {
        return identityClass
    }

    companion object{

        const val vocabName = "identity-class-ov"

        var vocab: LinkedHashSet<String> = linkedSetOf(
            "individual", "group", "system",
            "organization", "class", "unspecified")
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }

    init {
        require(this.identityClass in vocab)
    }
}