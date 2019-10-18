package com.stephenott.stix.type

class AlternateDataStreams(private val streams: List<AlternateDataStreamType>):
    List<AlternateDataStreamType> by streams{

    init {
        //@TODO
    }
}

data class AlternateDataStreamType(
    val name: String,
    val hashes: HashesDictionary,
    val size: StixInteger){
    init {
        require(size.value >= 0, lazyMessage = {"size must not be negative."})
    }
}