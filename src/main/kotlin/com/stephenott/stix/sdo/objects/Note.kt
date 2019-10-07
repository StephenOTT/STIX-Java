package com.stephenott.stix.sdo.objects

import com.stephenott.stix.sdo.StixDomainObject
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.KillChainPhases

interface NoteSdo : StixDomainObject {
    val abstract: String?
    val content: String
    val authors: StixStringList?
    val objectRefs: StixIdentifiers
}

data class Note(
    override val abstract: String? = null,
    override val content: String,
    override val authors: StixStringList? = null,
    override val objectRefs: StixIdentifiers,
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
) :
    NoteSdo {

    companion object {
        val stixType = StixType("note")
    }

}