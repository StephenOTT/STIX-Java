package com.stephenott.stix.type.vocab

class IndustrySectors(private val sectors: LinkedHashSet<IndustrySector> = linkedSetOf()) :
    Set<IndustrySector> by sectors {
}

class IndustrySector(private val sector: String) : OpenVocab {

    override fun getValue(): String {
        return sector
    }

    companion object {

        const val vocabName = "industry-sector-ov"

        var vocab: LinkedHashSet<String> = linkedSetOf(
            "agriculture", "aerospace", "automotive",
            "communications", "construction", "defence",
            "education", "energy", "entertainment",
            "financial-services", "government-national", "government-regional",
            "government-local", "government-public-services", "healthcare",
            "hospitality-leisure", "infrastructure", "insurance",
            "manufacturing", "mining", "non-profit",
            "pharmaceuticals", "retail", "technology",
            "telecommunications", "transportation", "utilities"
        )
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }

    init {
        require(this.sector in vocab)
    }
}