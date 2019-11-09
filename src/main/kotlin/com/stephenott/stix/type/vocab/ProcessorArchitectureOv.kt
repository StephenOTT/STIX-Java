package com.stephenott.stix.type.vocab

class ProcessorArchitectures(private val architectures: LinkedHashSet<ProcessorArchitecture> = linkedSetOf()) :
    Set<ProcessorArchitecture> by architectures {
}

class ProcessorArchitecture(private val architecture: String) : OpenVocab {

    override fun getValue(): String {
        return architecture
    }

    companion object {

        const val vocabName = "processor-architecture-ov"

        var vocab: LinkedHashSet<String> = linkedSetOf(
            "alpha", "arm", "ia-64",
            "mips", "powerpc", "sparc",
            "x86", "x86-64"
        )
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }

    init {
        require(this.architecture in vocab)
    }
}