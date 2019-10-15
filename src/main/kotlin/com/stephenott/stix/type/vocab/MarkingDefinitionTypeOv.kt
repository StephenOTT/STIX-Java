package com.stephenott.stix.type.vocab

data class MarkingDefinitionTypeOv(private val definitionType: String): OpenVocab, CharSequence by definitionType{

    companion object{

        val vocabName = "marking-definition-type-ov"

        var vocab: LinkedHashSet<String> = linkedSetOf(
            "statement", "tlp")
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }

    init {
        require(this.definitionType in vocab)
    }
}