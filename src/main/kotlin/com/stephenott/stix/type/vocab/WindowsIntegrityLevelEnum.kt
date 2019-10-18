package com.stephenott.stix.type.vocab

class WindowsIntegrityLevelEnum(private val level: String) : ClosedVocab, CharSequence by level {

    companion object {
        val vocabName = "windows-integrity-level-enum"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "low", "medium", "high",
            "system"
        )
    }

    init {
        require(this.level in vocab)
    }
}