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

    companion object : CompanionStixType,
        BusinessRulesValidator<IdentitySdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("identity")

        override fun objectValidationRules(obj: IdentitySdo, stixInstance: Stix) {
            requireStixType(this.stixType, obj)
        }

        override val allowedRelationships: List<AllowedRelationship> = listOf(
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
) : IdentitySdo {

    init {
        if (this.stixValidateOnConstruction) {
            IdentitySdo.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return IdentitySdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}
