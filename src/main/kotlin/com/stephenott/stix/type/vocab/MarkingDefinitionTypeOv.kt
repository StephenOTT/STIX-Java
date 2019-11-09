package com.stephenott.stix.type.vocab

data class MarkingDefinitionTypeOv(private val definitionType: String): OpenVocab {
    override fun getValue(): String {
        return definitionType
    }

    companion object{

        const val vocabName = "marking-definition-type-ov"

        var vocab: LinkedHashSet<String> = linkedSetOf(
            "statement", "tlp")
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }

    init {
        require(this.definitionType in vocab, lazyMessage = {"Marking Definition is using a unsupported definition_type"})
    }
}