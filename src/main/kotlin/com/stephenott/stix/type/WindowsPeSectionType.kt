package com.stephenott.stix.type

class WindowsPeSectionTypes(private val types: List<WindowsPeSectionType>): List<WindowsPeSectionType> by types{

}

data class WindowsPeSectionType(
    val name: String,
    val size: StixInteger?,
    val entropy: StixFloat?,
    val hashes: HashesDictionary?
) {

    init {
        require(size?.value!! >= 0)
    }

}