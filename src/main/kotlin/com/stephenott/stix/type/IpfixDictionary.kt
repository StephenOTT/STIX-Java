package com.stephenott.stix.type

class IpfixDictionary(private val dictionary: LinkedHashMap<String, IpFixElement>):
    Map<String, IpFixElement> by dictionary {

    init {
        //@TODO

        //@TODO Add a JsonCreator constructor for the Map

    }
}

interface IpFixElement{
    val value: Any
}

data class IpfixElementStringValue(override val value: String): IpFixElement

data class IpfixElementIntegerValue(override val value: StixInteger): IpFixElement