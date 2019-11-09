package com.stephenott.stix.type.vocab

class WindowsIntegrityLevelEnum(private val level: String) : ClosedVocab {
    override fun getValue(): String {
        return level
    }

    companion object {
        const val vocabName = "windows-integrity-level-enum"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "low", "medium", "high",
            "system"
        )
    }

    init {
        require(this.level in vocab)
    }
}