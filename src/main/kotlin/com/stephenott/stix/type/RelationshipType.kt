package com.stephenott.stix.type

data class RelationshipType(val type: String) {
    init {
        //@TODO
        //The value of this property MUST be in ASCII and is limited to characters a–z (lowercase ASCII), 0–9, and hyphen (-).
    }

    override fun toString(): String {
        return type
    }
}