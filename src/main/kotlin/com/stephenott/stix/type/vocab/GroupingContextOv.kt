package com.stephenott.stix.type.vocab

class GroupingContextOv(private val groupingContext: String) : OpenVocab, CharSequence by groupingContext {

    companion object {
        val vocabName = "grouping-context-ov"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "suspicious-activity", "malware-analysis", "unspecified"
        )
    }

    init {
        require(this.groupingContext in vocab)
    }
}