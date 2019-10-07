package com.stephenott.stix.type

data class StixSpecVersion(private val versionEnum: StixVersions, val isDefinedValue: Boolean) {

    companion object{
        enum class StixVersions(val version: String) {
            TWO_DOT_ZERO("2.0"),
            TWO_DOT_ONE("2.1")
        }

        fun parse(versionString: String): StixSpecVersion{
            return StixSpecVersion(StixVersions.values().first { it.version == versionString }, true)
        }
    }

    //@TODO Review if this is the correct default
    constructor() : this(StixVersions.TWO_DOT_ZERO, false)

    override fun toString(): String {
        return versionEnum.version
    }
}