package com.stephenott.stix.type.vocab

class OpinionEnum(private val opinion: String) : ClosedVocab, CharSequence by opinion {

    companion object {
        var vocab: LinkedHashSet<String> = linkedSetOf(
            "strongly-disagree", "disagree",
            "neutral", "agree", "strongly-agree"
        )
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }

    init {
        require(this.opinion in vocab)
    }
}