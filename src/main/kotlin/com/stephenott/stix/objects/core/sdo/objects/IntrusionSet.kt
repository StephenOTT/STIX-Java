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

interface IntrusionSetSdo : StixDomainObject {
    val name: String
    val description: String?
    val aliases: StixStringList?
    val firstSeen: StixTimestamp?
    val lastSeen: StixTimestamp?
    val goals: StixStringList?
    val resourceLevel: AttackResourceLevelOv?
    val primaryMotivation: AttackMotivationOv?
    val secondaryMotivations: AttackMotivations?

    companion object : CompanionStixType,
        BusinessRulesValidator<IntrusionSetSdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("intrusion-set")

        override fun objectValidationRules(obj: IntrusionSetSdo, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

            if (obj.firstSeen != null && obj.lastSeen != null){
                require(obj.lastSeen!!.instant >= obj.firstSeen!!.instant,
                    lazyMessage = {"last_seen must be equal or greater than first_seen."})
            }
        }

        override val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                IntrusionSetSdo::class,
                RelationshipType("attributed-to"),
                ThreatActorSdo::class
            ),
            AllowedRelationship(
                IntrusionSetSdo::class,
                RelationshipType("compromises"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                IntrusionSetSdo::class,
                RelationshipType("hosts"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                IntrusionSetSdo::class,
                RelationshipType("owns"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                IntrusionSetSdo::class,
                RelationshipType("originates-from"),
                LocationSdo::class
            ),
            AllowedRelationship(
                IntrusionSetSdo::class,
                RelationshipType("targets"),
                IdentitySdo::class
            ),
            AllowedRelationship(
                IntrusionSetSdo::class,
                RelationshipType("targets"),
                LocationSdo::class
            ),
            AllowedRelationship(
                IntrusionSetSdo::class,
                RelationshipType("targets"),
                VulnerabilitySdo::class
            ),
            AllowedRelationship(
                IntrusionSetSdo::class,
                RelationshipType("uses"),
                AttackPatternSdo::class
            ),
            AllowedRelationship(
                IntrusionSetSdo::class,
                RelationshipType("uses"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                IntrusionSetSdo::class,
                RelationshipType("uses"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                IntrusionSetSdo::class,
                RelationshipType("uses"),
                ToolSdo::class
            )
        )
    }
}

data class IntrusionSet(
        override val name: String,
        override val description: String? = null,
        override val aliases: StixStringList? = null,
        override val firstSeen: StixTimestamp? = null,
        override val lastSeen: StixTimestamp? = null,
        override val goals: StixStringList? = null,
        override val resourceLevel: AttackResourceLevelOv? = null,
        override val primaryMotivation: AttackMotivationOv? = null,
        override val secondaryMotivations: AttackMotivations? = null,
        override val type: StixType = IntrusionSetSdo.stixType,
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
) : IntrusionSetSdo {

    init {
        if (this.stixValidateOnConstruction) {
            IntrusionSetSdo.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return IntrusionSetSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}