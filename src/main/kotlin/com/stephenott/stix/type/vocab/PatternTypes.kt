package com.stephenott.stix.type.vocab

class PatternType(private val type: String) : ClosedVocab, CharSequence by type {

    companion object {
        val vocab: LinkedHashSet<String> = linkedSetOf(
            "stix", "snort", "yara"
        )
    }

    init {
        require(this.type in vocab)
    }
}