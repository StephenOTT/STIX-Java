package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.*

interface IntrusionSetSdo : StixDomainObject {
    val name: String
    val description: String?
    val aliases: StixStringList?
    val firstSeen: StixInstant?
    val lastSeen: StixInstant?
    val goals: StixStringList?
    val resourceLevel: AttackResourceLevelOv?
    val primaryMotivation: AttackMotivationOv?
    val secondaryMotivations: AttackMotivations?

    companion object{
        val stixType = StixType("intrusion-set")

        val allowedRelationships: List<AllowedRelationship> = listOf(
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
    override val firstSeen: StixInstant? = null,
    override val lastSeen: StixInstant? = null,
    override val goals: StixStringList? = null,
    override val resourceLevel: AttackResourceLevelOv? = null,
    override val primaryMotivation: AttackMotivationOv? = null,
    override val secondaryMotivations: AttackMotivations? = null,
    override val type: StixType = IntrusionSetSdo.stixType,
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
) :
    IntrusionSetSdo {

    override fun allowedRelationships(): List<AllowedRelationship> {
        return IntrusionSetSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}