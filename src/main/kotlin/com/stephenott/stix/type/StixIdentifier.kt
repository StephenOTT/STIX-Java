package com.stephenott.stix.type

import java.util.*
import kotlin.collections.LinkedHashSet

class StixIdentifiers(private val identifiers: LinkedHashSet<StixIdentifier>) :
    Set<StixIdentifier> by identifiers {

    //@TODO add limits on which Identifiers can be added for specific implementations
}

data class StixIdentifier(
    val type: StixType,
    val uuid: String = generateUUIDv4()
) {

    constructor(type: String) : this(StixType(type))

    //@TODO Add deterministic ID generation

    companion object {
        fun generateUUIDv4(): String = UUID.randomUUID().toString()
        const val typeUUIDSpacer = "--"

        fun parse(stringIdentifier: String): StixIdentifier{
            val type: String = stringIdentifier.substringBefore(typeUUIDSpacer)
            val uuid: String = stringIdentifier.substringAfter(typeUUIDSpacer)
            return StixIdentifier(StixType(type), uuid)
        }

    }

    fun getIdentifier(): String {
        return type.toString() + typeUUIDSpacer + uuid
    }

    override fun toString(): String {
        return getIdentifier()
    }
}