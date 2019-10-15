package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.OpinionEnum

interface OpinionSdo : StixDomainObject {
    val explanation: String?
    val authors: StixStringList?
    val opinion: OpinionEnum
    val objectRefs: StixIdentifiers

    companion object{
        val stixType = StixType("opinion")

        val allowedRelationships: List<AllowedRelationship> = listOf()
    }
}

data class Opinion(
    override val explanation: String? = null,
    override val authors: StixStringList? = null,
    override val opinion: OpinionEnum,
    override val objectRefs: StixIdentifiers,
    override val type: StixType = OpinionSdo.stixType,
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
    OpinionSdo {
    override fun allowedRelationships(): List<AllowedRelationship> {
        return OpinionSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}