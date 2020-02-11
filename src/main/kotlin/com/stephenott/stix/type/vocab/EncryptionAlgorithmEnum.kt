package com.stephenott.stix.type.vocab

class EncryptionAlgorithmEnum(private val algorithm: String) : OpenVocab {

    override fun getValue(): String {
        return algorithm
    }

    companion object {
        const val vocabName = "encryption-algorithm-enum"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "AES-256-GCM​", "ChaCha20-Poly1305", "mime-type-indicated"
        )
    }

    init {
        require(this.algorithm in vocab)
    }
}