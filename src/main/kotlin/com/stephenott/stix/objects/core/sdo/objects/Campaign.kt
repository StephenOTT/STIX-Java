package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*

interface CampaignSdo : StixDomainObject {
    val name: String
    val description: String?
    val aliases: String?
    val firstSeen: StixInstant?
    val lastSeen: StixInstant?
    val objective: String?

    companion object{
        val stixType = StixType("campaign")

        val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                CampaignSdo::class,
                RelationshipType("attributed-to"),
                IntrusionSetSdo::class
            ),
            AllowedRelationship(
                CampaignSdo::class,
                RelationshipType("attributed-to"),
                ThreatActorSdo::class
            ),

            AllowedRelationship(
                CampaignSdo::class,
                RelationshipType("compromises"),
                InfrastructureSdo::class
            ),

            AllowedRelationship(
                CampaignSdo::class,
                RelationshipType("originates-from"),
                LocationSdo::class
            ),

            AllowedRelationship(
                CampaignSdo::class,
                RelationshipType("targets"),
                IdentitySdo::class
            ),
            AllowedRelationship(
                CampaignSdo::class,
                RelationshipType("targets"),
                LocationSdo::class
            ),
            AllowedRelationship(
                CampaignSdo::class,
                RelationshipType("targets"),
                VulnerabilitySdo::class
            ),

            AllowedRelationship(
                CampaignSdo::class,
                RelationshipType("uses"),
                AttackPatternSdo::class
            ),
            AllowedRelationship(
                CampaignSdo::class,
                RelationshipType("uses"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                CampaignSdo::class,
                RelationshipType("uses"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                CampaignSdo::class,
                RelationshipType("uses"),
                ToolSdo::class
            )
        )
    }

}

data class Campaign
    (
    override val name: String,
    override val description: String? = null,
    override val aliases: String? = null,
    override val firstSeen: StixInstant? = null,
    override val lastSeen: StixInstant? = null,
    override val objective: String? = null,
    override val type: StixType = CampaignSdo.stixType,
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
) : CampaignSdo {

    override fun allowedRelationships(): List<AllowedRelationship> {
        return CampaignSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}