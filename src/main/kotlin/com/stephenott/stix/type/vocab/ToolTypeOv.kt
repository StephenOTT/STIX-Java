package com.stephenott.stix.type.vocab

class ToolTypes(private val types: LinkedHashSet<ToolType> = linkedSetOf()) :
    Set<ToolType> by types {
}

class ToolType(private val type: String) : OpenVocab {

    override fun getValue(): String {
        return type
    }

    companion object {
        var vocab: LinkedHashSet<String> = linkedSetOf(
            "denial-of-service", "exploitation", "information-gathering",
            "network-capture", "credential-exploitation", "remote-access",
            "vulnerability-scanning", "unknown"
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