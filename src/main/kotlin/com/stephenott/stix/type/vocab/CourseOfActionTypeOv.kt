package com.stephenott.stix.type.vocab

class CourseOfActionTypeOv(private val type: String) : ClosedVocab {

    override fun getValue(): String {
        return type
    }

    companion object {
        const val vocabName = "course-of-action-type-ov"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "textual:text/plain", "textual:text/html", "textual:text/md",
            "textual:pdf"
        )
    }

    init {
        require(this.type in vocab)
    }
}