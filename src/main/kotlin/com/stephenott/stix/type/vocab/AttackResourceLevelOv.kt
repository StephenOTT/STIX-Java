package com.stephenott.stix.type.vocab

class AttackResourceLevelOv(private val level: String) : OpenVocab, CharSequence by level {

    override fun getValue(): String {
        return level
    }

    companion object {
        const val vocabName = "attack-resource-level-ov"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "individual", "club", "contest",
            "team", "organization", "government"
        )
    }

    init {
        require(this.level in vocab)
    }
}