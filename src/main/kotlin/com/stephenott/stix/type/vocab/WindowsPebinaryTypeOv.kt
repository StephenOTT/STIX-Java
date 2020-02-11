package com.stephenott.stix.type.vocab

class WindowsPebinaryTypeOv(private val type: String) : ClosedVocab {

    override fun getValue(): String {
        return type
    }

    companion object {
        const val vocabName = "windows-pebinary-type-ov"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "dll", "exe", "sys"
        )
    }

    init {
        require(this.type in vocab)
    }
}