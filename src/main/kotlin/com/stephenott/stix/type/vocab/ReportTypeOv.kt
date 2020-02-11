package com.stephenott.stix.type.vocab

class ReportTypes(private val types: LinkedHashSet<ReportType> = linkedSetOf()) :
    Set<ReportType> by types {
}

class ReportType(private val type: String) : OpenVocab {
    override fun getValue(): String {
        return type
    }

    companion object {

        const val vocabName = "report-type-ov"

        var vocab: LinkedHashSet<String> = linkedSetOf(
            "attack-pattern", "campaign", "identity",
            "indicator", "intrusion-set", "malware",
            "observed-data", "threat-actor", "threat-report",
            "tool", "vulnerability"
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