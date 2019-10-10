package com.stephenott.stix.`object`.sdo.`object`

import com.stephenott.stix.`object`.sdo.StixDomainObject
import com.stephenott.stix.`object`.sro.`object`.AllowedRelationship
import com.stephenott.stix.`object`.sro.`object`.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.KillChainPhases
import com.stephenott.stix.type.vocab.ToolTypes

interface ToolSdo : StixDomainObject {
    val name: String
    val description: String?
    val toolTypes: ToolTypes
    val aliases: StixStringList?
    val killChainPhases: KillChainPhases?
    val toolVersion: String?

    companion object{
        val stixType = StixType("tool")

        val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("delivers"),
                MalwareSdo::class
            ),

            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("drops"),
                MalwareSdo::class
            ),

            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("has"),
                VulnerabilitySdo::class
            ),

            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("targets"),
                IdentitySdo::class
            ),
            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("targets"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("targets"),
                LocationSdo::class
            ),
            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("targets"),
                VulnerabilitySdo::class
            ),

            AllowedRelationship(
                ToolSdo::class,
                RelationshipType("uses"),
                InfrastructureSdo::class
            )
        )
    }
}

data class Tool(
    override val name: String,
    override val description: String? = null,
    override val toolTypes: ToolTypes,
    override val aliases: StixStringList? = null,
    override val killChainPhases: KillChainPhases? = null,
    override val toolVersion: String? = null,
    override val type: StixType = ToolSdo.stixType,
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
    ToolSdo {
    override fun allowedRelationships(): List<AllowedRelationship> {
        return ToolSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}