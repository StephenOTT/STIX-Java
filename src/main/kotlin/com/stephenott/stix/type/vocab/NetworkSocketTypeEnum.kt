package com.stephenott.stix.type.vocab

class NetworkSocketTypeEnum(private val type: String) : ClosedVocab {

    override fun getValue(): String {
        return type
    }

    companion object {
        const val vocabName = "network-socket-type-enum"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "SOCK_STREAM", "AF_ISOCK_DGRAMNET", "SOCK_RAW",
            "SOCK_RDM", "SOCK_SEQPACKET"
        )
    }

    init {
        require(this.type in vocab)
    }
}