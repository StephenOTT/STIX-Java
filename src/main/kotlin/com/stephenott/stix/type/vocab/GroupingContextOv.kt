package com.stephenott.stix.type.vocab

class GroupingContextOv(private val groupingContext: String) : OpenVocab {

    override fun getValue(): String {
        return groupingContext
    }

    companion object {
        const val vocabName = "grouping-context-ov"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "suspicious-activity", "malware-analysis", "unspecified"
        )
    }

    init {
        require(this.groupingContext in vocab)
    }
}