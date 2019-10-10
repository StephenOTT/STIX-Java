package com.stephenott.stix.`object`.sdo.`object`

import com.stephenott.stix.`object`.sdo.StixDomainObject
import com.stephenott.stix.`object`.sro.`object`.AllowedRelationship
import com.stephenott.stix.`object`.sro.`object`.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.IdentityClass
import com.stephenott.stix.type.vocab.IdentityRoles
import com.stephenott.stix.type.vocab.IndustrySectors

interface IdentitySdo : StixDomainObject {
    val name: String
    val description: String?
    val roles: IdentityRoles?
    val identityClass: IdentityClass?
    val sectors: IndustrySectors?
    val contactInformation: String?

    companion object{
        val stixType = StixType("identity")

        val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                IdentitySdo::class,
                RelationshipType("located-at"),
                LocationSdo::class
            )
        )
    }
}

data class Identity(
    override val name: String,
    override val description: String? = null,
    override val roles: IdentityRoles? = null,
    override val identityClass: IdentityClass? = null,
    override val sectors: IndustrySectors? = null,
    override val contactInformation: String? = null,
    override val type: StixType = IdentitySdo.stixType,
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
) : IdentitySdo {

    override fun allowedRelationships(): List<AllowedRelationship> {
        return IdentitySdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}
