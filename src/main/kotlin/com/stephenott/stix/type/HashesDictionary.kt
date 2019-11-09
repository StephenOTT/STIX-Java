package com.stephenott.stix.type

import com.stephenott.stix.type.vocab.OpenVocab

class HashesDictionary(private val immutableDictionary: LinkedHashMap<HashingAlgorithm, String> = linkedMapOf()):
    Map<HashingAlgorithm, String> by immutableDictionary {
}

class HashingAlgorithm(private val algorithm: String): OpenVocab {
    override fun getValue(): String {
        return algorithm
    }

    companion object{
        const val vocabName: String = "hash-algorithm-ov​"

        var vocab: LinkedHashSet<String> = linkedSetOf(
                "MD5", "MD6", "RIPEMD-160", "SHA-1",
                "SHA-224", "SHA-256", "SHA-384", "SHA-512",
                "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512",
                "SSDEEP", "WHIRLPOOL")
            set(value) {
                require(value.all { it.length in 3..256 })
                field = value
            }
    }

    init {
        require(this.algorithm in vocab)
    }
}