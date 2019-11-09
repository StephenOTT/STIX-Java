package com.stephenott.stix.type.vocab

class AdministrativeAreas(private val areas: LinkedHashSet<AdministrativeArea> = linkedSetOf(), enforceVocab: Boolean = false) :
    Set<AdministrativeArea> by areas {

    init {
        if (enforceVocab) {
            areas.all { AdministrativeArea.vocab.contains(it.toString()) }
        }
    }
}

class AdministrativeArea(private val area: String, enforceVocab: Boolean = false) : OpenVocab {

    override fun getValue(): String {
        return area
    }

    init {
        if (enforceVocab) {
            require(vocab.contains(area))
        }
    }

    companion object {

        const val vocabName = "administrative-areas"

        var vocab: LinkedHashSet<String> = linkedSetOf(
        )
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }
}