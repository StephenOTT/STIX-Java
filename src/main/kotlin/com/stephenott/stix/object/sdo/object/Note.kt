package com.stephenott.stix.`object`.sdo.`object`

import com.stephenott.stix.`object`.sdo.StixDomainObject
import com.stephenott.stix.`object`.sro.`object`.AllowedRelationship
import com.stephenott.stix.`object`.sro.`object`.RelationshipSro
import com.stephenott.stix.type.*

interface NoteSdo : StixDomainObject {
    val abstract: String?
    val content: String
    val authors: StixStringList?
    val objectRefs: StixIdentifiers

    companion object{
        val stixType = StixType("note")

        val allowedRelationships: List<AllowedRelationship> = listOf()
    }

}

data class Note(
    override val abstract: String? = null,
    override val content: String,
    override val authors: StixStringList? = null,
    override val objectRefs: StixIdentifiers,
    override val type: StixType = NoteSdo.stixType,
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
    NoteSdo {
    override fun allowedRelationships(): List<AllowedRelationship> {
        return NoteSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}