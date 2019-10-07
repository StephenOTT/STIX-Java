package com.stephenott.stix.sdo.objects

import com.stephenott.stix.sdo.StixDomainObject
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
}

data class Identity(
    override val name: String,
    override val description: String? = null,
    override val roles: IdentityRoles? = null,
    override val identityClass: IdentityClass? = null,
    override val sectors: IndustrySectors? = null,
    override val contactInformation: String? = null,
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
    ) : IdentitySdo {

    companion object {
        val stixType = StixType("identity")
    }

}