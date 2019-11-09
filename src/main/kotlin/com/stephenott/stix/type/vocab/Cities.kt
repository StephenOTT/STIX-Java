package com.stephenott.stix.type.vocab

class Cities(private val cities: LinkedHashSet<City> = linkedSetOf(), enforceVocab: Boolean = false) :
    Set<City> by cities {

    init {
        if (enforceVocab) {
            cities.all { City.vocab.contains(it.toString()) }
        }
    }
}

class City(private val city: String, enforceVocab: Boolean = false) : OpenVocab, CharSequence by city {

    override fun getValue(): String {
        return city
    }

    init {
        if (enforceVocab) {
            require(vocab.contains(city))
        }
    }

    companion object {

        const val vocabName = "cities"

        var vocab: LinkedHashSet<String> = linkedSetOf(
        )
            set(value) {
                require(value.isNotEmpty())
                field = value
            }
    }
}