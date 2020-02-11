package com.stephenott.stix.type.vocab

class ThreatActorTypes(private val types: LinkedHashSet<ThreatActorType> = linkedSetOf()) :
    Set<ThreatActorType> by types {
}

class ThreatActorType(private val type: String) : OpenVocab {

    override fun getValue(): String {
        return type
    }

    companion object {

        const val vocabName = "threat-actor-type-ov"

        var vocab: LinkedHashSet<String> = linkedSetOf(
            "activist", "competitor", "crime-syndicate",
            "criminal", "hacker", "insider-accidental",
            "insider-disgruntled", "nation-state", "sensationalist",
            "spy", "terrorist", "unknown"
        )
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }

    init {
        require(this.type in vocab)
    }
}