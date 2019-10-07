package com.stephenott.stix.sdo.objects

import com.stephenott.stix.sdo.StixDomainObject
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.IndicatorTypes
import com.stephenott.stix.type.vocab.PatternType

interface IndicatorSdo : StixDomainObject {
    val name: String?
    val description: String?
    val indicatorTypes: IndicatorTypes
    val pattern: StixPattern
    val patternType: PatternType
    val patternVersion: String?

}

data class Indicator(
    override val name: String? = null,
    override val description: String? = null,
    override val indicatorTypes: IndicatorTypes,
    override val pattern: StixPattern,
    override val patternType: PatternType,
    override val patternVersion: String? = null,
    override val type: StixType = stixType,
    override val id: StixIdentifier = StixIdentifier(type),
    override val createdByRef: String? = null,
    override val created: StixInstant = StixInstant(),
    override val externalReferences: ExternalReferences? = null,
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(),
    override val labels: StixLabels? = null,
    override val modified: StixInstant = StixInstant(created),
    override val revoked: StixBoolean = StixBoolean()
) : IndicatorSdo {

    companion object {
        val stixType = StixType("indicator")
    }

    init {
        require(indicatorTypes.size >= 1)
    }

}