package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.GroupingContextOv

interface GroupingSdo : StixDomainObject {
    val name: String?
    val description: String?
    val context: GroupingContextOv
    val objectRefs: StixIdentifiers

    companion object{
        val stixType = StixType("grouping")

        val allowedRelationships: List<AllowedRelationship> = listOf()
    }
}

data class Grouping(
    override val name: String? = null,
    override val description: String? = null,
    override val context: GroupingContextOv,
    override val objectRefs: StixIdentifiers,
    override val type: StixType = GroupingSdo.stixType,
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
    GroupingSdo {

    override fun allowedRelationships(): List<AllowedRelationship> {
        return GroupingSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}