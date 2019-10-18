package com.stephenott.stix.type.vocab

class WindowsPebinaryTypeOv(private val type: String) : ClosedVocab, CharSequence by type {

    companion object {
        val vocabName = "windows-pebinary-type-ov"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "dll", "exe", "sys"
        )
    }

    init {
        require(this.type in vocab)
    }
}