package com.stephenott.stix.`object`.sdo.`object`

import com.stephenott.stix.`object`.sdo.StixDomainObject
import com.stephenott.stix.`object`.sro.`object`.AllowedRelationship
import com.stephenott.stix.`object`.sro.`object`.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.KillChainPhases

interface AttackPatternSdo : StixDomainObject {
    val name: String
    val description: String?
    val killChainPhases: KillChainPhases? //@TODO

    companion object {
        val stixType = StixType("attack-pattern")

        val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                AttackPatternSdo::class,
                RelationshipType("delivers"),
                MalwareSdo::class
            ),

            AllowedRelationship(
                AttackPatternSdo::class,
                RelationshipType("targets"),
                IdentitySdo::class
            ),
            AllowedRelationship(
                AttackPatternSdo::class,
                RelationshipType("targets"),
                LocationSdo::class
            ),
            AllowedRelationship(
                AttackPatternSdo::class,
                RelationshipType("targets"),
                VulnerabilitySdo::class
            ),

            AllowedRelationship(
                AttackPatternSdo::class,
                RelationshipType("uses"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                AttackPatternSdo::class,
                RelationshipType("uses"),
                ToolSdo::class
            )
        )
    }

}

data class AttackPattern(
    override val name: String,
    override val description: String? = null,
    override val killChainPhases: KillChainPhases? = null,
    override val type: StixType = AttackPatternSdo.stixType,
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
) :
    AttackPatternSdo {

    override fun allowedRelationships(): List<AllowedRelationship> {
       return AttackPatternSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}