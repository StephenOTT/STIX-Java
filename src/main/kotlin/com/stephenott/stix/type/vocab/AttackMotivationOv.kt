package com.stephenott.stix.type.vocab

class AttackMotivations(private val motivations: LinkedHashSet<AttackMotivationOv> = linkedSetOf()) :
    Set<AttackMotivationOv> by motivations {
}

class AttackMotivationOv(private val motivation: String) : OpenVocab {
    override fun getValue(): String {
        return motivation
    }

    companion object {
        const val vocabName = "attack-motivation-ov"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "accidental", "coercion", "dominance",
            "ideology", "notoriety", "organizational-gain",
            "personal-gain", "personal-satisfaction",
            "revenge", "unpredictable"
        )
    }

    init {
        require(this.motivation in vocab)
    }
}