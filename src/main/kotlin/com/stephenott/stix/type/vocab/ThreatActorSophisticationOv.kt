package com.stephenott.stix.type.vocab

class ThreatActorSophisticationOv(private val sophistication: String) : OpenVocab {

    override fun getValue(): String {
        return sophistication
    }

    companion object {

        const val vocabName = "threat-actor-sophistication"

        var vocab: LinkedHashSet<String> = linkedSetOf(
            "none", "minimal", "intermediate",
            "advanced", "expert", "innovator",
            "strategic"
        )
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }

    init {
        require(this.sophistication in vocab)
    }
}