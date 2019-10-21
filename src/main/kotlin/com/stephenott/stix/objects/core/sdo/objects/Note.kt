package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*

interface NoteSdo : StixDomainObject {
    val abstract: String?
    val content: String
    val authors: StixStringList?
    val objectRefs: StixIdentifiers

    companion object : CompanionStixType,
        BusinessRulesValidator<NoteSdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("note")

        override fun objectValidationRules(obj: NoteSdo) {

        }

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )
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
    override val revoked: StixBoolean = StixBoolean(),
    override val confidence: StixConfidence? = null,
    override val lang: StixLang? = null
) :
    NoteSdo {

    init {
        NoteSdo.objectValidationRules(this)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return NoteSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}