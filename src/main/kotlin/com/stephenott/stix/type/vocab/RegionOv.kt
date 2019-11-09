package com.stephenott.stix.type.vocab

class RegionOv(private val region: String) : ClosedVocab {

    override fun getValue(): String {
        return region
    }

    companion object {
        const val vocabName = "region-ov"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "africa", "eastern-africa", "middle-africa",
            "northern-africa", "southern-africa", "western-africa",
            "americas", "latin-america-caribbean", "south-america",
            "caribbean", "central-america",  "northern-america",
            "asia", "central-asia", "eastern-asia",
            "southern-asia", "western-asia", "europe",
            "eastern-europe", "northern-europe", "southern-europe",
            "western-europe", "oceania", "australia-new-zealand",
            "melanesia", "micronesia", "polynesia", "antarctica"
        )
    }

    init {
        require(this.region in vocab)
    }
}