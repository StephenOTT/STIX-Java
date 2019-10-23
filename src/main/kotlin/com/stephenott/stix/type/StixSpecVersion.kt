package com.stephenott.stix.type

import java.lang.IllegalArgumentException

data class StixSpecVersion(private val versionEnum: StixVersions, val isDefinedValue: Boolean) {

    companion object{
        enum class StixVersions(val version: String) {
//            TWO_DOT_ZERO("2.0"),  //@TODO review this
            TWO_DOT_ONE("2.1")
        }

        fun parse(versionString: String): StixSpecVersion{
            try {
                return StixSpecVersion(StixVersions.values().first { it.version == versionString }, true)
            } catch (e: Exception){
                throw IllegalArgumentException("Unable to parse spec version.  Note that only spec version 2.1 is supported.  Use an elevator to pre-convert 2.0 to 2.1", e)
            }

        }
    }

    //@TODO Review if this is the correct default
    constructor() : this(StixVersions.TWO_DOT_ONE, true)

    override fun toString(): String {
        return versionEnum.version
    }
}