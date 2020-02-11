package com.stephenott.stix.type.vocab

class InfrastructureTypes(private val types: LinkedHashSet<InfrastructureType> = linkedSetOf()) :
    Set<InfrastructureType> by types {
}

class InfrastructureType(private val type: String) : OpenVocab {

    override fun getValue(): String {
        return type
    }

    companion object {

        const val vocabName = "infrastructure-type-ov"

        var vocab: LinkedHashSet<String> = linkedSetOf(
            "amplification", "anonymization", "botnet",
            "command-and-control", "exfiltration", "hosting-malware",
            "hosting-target-lists", "phishing", "reconnaissance",
            "staging", "undefined"
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