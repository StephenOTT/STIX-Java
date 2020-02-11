package com.stephenott.stix.type.vocab

class PatternType(private val type: String) : ClosedVocab {

    override fun getValue(): String {
        return type
    }

    companion object {

        const val vocabName = "pattern-type" //@TODO

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "stix", "snort", "yara"
        )
    }

    init {
        require(this.type in vocab)
    }
}