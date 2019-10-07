package com.stephenott.stix.type.vocab

class IndicatorTypes(private val types: LinkedHashSet<IndicatorType> = linkedSetOf()) :
    Set<IndicatorType> by types {
}

class IndicatorType(private val type: String) : OpenVocab, CharSequence by type {

    companion object {

        val vocabName = "indicator-type-ov"

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