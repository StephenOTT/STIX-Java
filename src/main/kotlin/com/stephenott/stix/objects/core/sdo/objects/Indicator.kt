package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.common.requireStixType
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.IndicatorTypes
import com.stephenott.stix.type.vocab.KillChainPhases
import com.stephenott.stix.type.vocab.PatternType

interface IndicatorSdo : StixDomainObject {
    val name: String?
    val description: String?
    val indicatorTypes: IndicatorTypes
    val pattern: StixPattern
    val patternType: PatternType
    val patternVersion: String?
    val validFrom: StixInstant
    val validUntil: StixInstant?
    val killChainPhases: KillChainPhases?

    companion object : CompanionStixType,
        BusinessRulesValidator<IndicatorSdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("indicator")

        override fun objectValidationRules(obj: IndicatorSdo) {
            requireStixType(this.stixType, obj)

            require(obj.validUntil?.instant!!.isAfter(obj.validFrom.instant),
                lazyMessage = {"valid_until must come after valid_from."})
        }

        override val allowedRelationships: List<AllowedRelationship> = listOf(
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
    override val validFrom: StixInstant,
    override val validUntil: StixInstant?,
    override val killChainPhases: KillChainPhases?,
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
    override val revoked: StixBoolean = StixBoolean(),
    override val confidence: StixConfidence? = null,
    override val lang: StixLang? = null
) : IndicatorSdo {

    init {
        IndicatorSdo.objectValidationRules(this)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return IndicatorSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }
}