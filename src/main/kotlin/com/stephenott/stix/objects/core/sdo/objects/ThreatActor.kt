package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.common.requireStixType
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.*

interface ThreatActorSdo : StixDomainObject {
    val name: String
    val description: String?
    val threatActorTypes: ThreatActorTypes
    val aliases: StixStringList?
    val firstSeen: StixTimestamp?
    val lastSeen: StixTimestamp?
    val roles: ThreatActorRoles?
    val goals: StixStringList?
    val sophistication: ThreatActorSophisticationOv?
    val resourceLevel: AttackResourceLevelOv?
    val primaryMotivation: AttackMotivationOv?
    val secondaryMotivation: AttackMotivationOv?
    val personalMotivations: AttackMotivations?

    companion object : CompanionStixType,
        BusinessRulesValidator<ThreatActorSdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("threat-actor")

        override fun objectValidationRules(obj: ThreatActorSdo, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

            if (obj.firstSeen != null && obj.lastSeen != null){
                require(obj.lastSeen!!.instant >= obj.firstSeen!!.instant,
                    lazyMessage = {"last_seen must be greater than or equal to first_Seen."})
            }
        }

        override val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("attributed-to"),
                IdentitySdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("compromises"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("hosts"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("owns"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("impersonates"),
                IdentitySdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("located-at"),
                LocationSdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("targets"),
                IdentitySdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("targets"),
                LocationSdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("targets"),
                VulnerabilitySdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("uses"),
                AttackPatternSdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("uses"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("uses"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("uses"),
                ToolSdo::class
            )
        )
    }
}

data class ThreatActor(
        override val name: String,
        override val description: String? = null,
        override val threatActorTypes: ThreatActorTypes,
        override val aliases: StixStringList? = null,
        override val firstSeen: StixTimestamp? = null,
        override val lastSeen: StixTimestamp? = null,
        override val roles: ThreatActorRoles? = null,
        override val goals: StixStringList? = null,
        override val sophistication: ThreatActorSophisticationOv? = null,
        override val resourceLevel: AttackResourceLevelOv? = null,
        override val primaryMotivation: AttackMotivationOv? = null,
        override val secondaryMotivation: AttackMotivationOv? = null,
        override val personalMotivations: AttackMotivations? = null,
        override val type: StixType = ThreatActorSdo.stixType,
        override val id: StixIdentifier = StixIdentifier(type),
        override val createdByRef: String? = null,
        override val created: StixTimestamp = StixTimestamp(),
        override val externalReferences: ExternalReferences? = null,
        override val objectMarkingsRefs: String? = null,
        override val granularMarkings: String? = null,
        override val specVersion: StixSpecVersion = StixSpecVersion(),
        override val labels: StixLabels? = null,
        override val modified: StixTimestamp = StixTimestamp(created),
        override val revoked: StixBoolean = StixBoolean(),
        override val confidence: StixConfidence? = null,
        override val lang: StixLang? = null,
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : ThreatActorSdo {

    init {
        if (this.stixValidateOnConstruction) {
            ThreatActorSdo.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return ThreatActorSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}