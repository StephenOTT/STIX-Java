package com.stephenott.stix.`object`.sdo.`object`

import com.stephenott.stix.`object`.sdo.StixDomainObject
import com.stephenott.stix.`object`.sro.`object`.AllowedRelationship
import com.stephenott.stix.`object`.sro.`object`.RelationshipSro
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

    companion object{
        val stixType = StixType("indicator")

        val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                IndicatorSdo::class,
                RelationshipType("indicates"),
                AttackPatternSdo::class
            ),
            AllowedRelationship(
                IndicatorSdo::class,
                RelationshipType("indicates"),
                CampaignSdo::class
            ),
            AllowedRelationship(
                IndicatorSdo::class,
                RelationshipType("indicates"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                IndicatorSdo::class,
                RelationshipType("indicates"),
                IntrusionSetSdo::class
            ),
            AllowedRelationship(
                IndicatorSdo::class,
                RelationshipType("indicates"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                IndicatorSdo::class,
                RelationshipType("indicates"),
                ThreatActorSdo::class
            ),
            AllowedRelationship(
                IndicatorSdo::class,
                RelationshipType("indicates"),
                ToolSdo::class
            ),

            AllowedRelationship(
                IndicatorSdo::class,
                RelationshipType("based-on"),
                ObservedDataSdo::class
            )
        )
    }
}

data class Indicator(
    override val name: String? = null,
    override val description: String? = null,
    override val indicatorTypes: IndicatorTypes,
    override val pattern: StixPattern,
    override val patternType: PatternType,
    override val patternVersion: String? = null,
    override val type: StixType = IndicatorSdo.stixType,
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

    override fun allowedRelationships(): List<AllowedRelationship> {
        return IndicatorSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

    init {
        require(indicatorTypes.size >= 1)
    }

}