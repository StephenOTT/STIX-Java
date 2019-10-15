package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.ReportTypes

interface ReportSdo : StixDomainObject {
    val name: String
    val description: String?
    val reportTypes: ReportTypes
    val published: StixInstant
    val objectRefs: StixIdentifiers

    companion object{
        val stixType = StixType("report")

        val allowedRelationships: List<AllowedRelationship> = listOf()
    }
}

data class Report(
    override val name: String,
    override val description: String? = null,
    override val reportTypes: ReportTypes,
    override val published: StixInstant,
    override val objectRefs: StixIdentifiers,
    override val type: StixType = ReportSdo.stixType,
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
    ReportSdo {

    override fun allowedRelationships(): List<AllowedRelationship> {
        return ReportSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}