package com.stephenott.stix.type.vocab

class IndicatorTypes(private val types: LinkedHashSet<IndicatorType>) :
    Set<IndicatorType> by types {

    init {
        require(types.size > 0)
    }
}

class IndicatorType(private val type: String) : OpenVocab {
    override fun getValue(): String {
        return type
    }

    companion object {

        const val vocabName = "indicator-type-ov"

        var vocab: LinkedHashSet<String> = linkedSetOf(
            "anomalous-activity", "anonymization", "benign",
            "compromised", "malicious-activity", "attribution",
            "unknown"
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