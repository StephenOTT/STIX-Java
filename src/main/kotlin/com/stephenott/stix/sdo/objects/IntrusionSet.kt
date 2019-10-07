package com.stephenott.stix.sdo.objects

import com.stephenott.stix.sdo.StixDomainObject
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.*

interface IntrusionSetSdo : StixDomainObject {
    val name: String
    val description: String?
    val aliases: StixStringList?
    val firstSeen: StixInstant?
    val lastSeen: StixInstant?
    val goals: StixStringList?
    val resourceLevel: AttackResourceLevelOv?
    val primaryMotivation: AttackMotivationOv?
    val secondaryMotivations: AttackMotivations?
}

data class IntrusionSet
    (
    override val name: String,
    override val description: String? = null,
    override val aliases: StixStringList? = null,
    override val firstSeen: StixInstant? = null,
    override val lastSeen: StixInstant? = null,
    override val goals: StixStringList? = null,
    override val resourceLevel: AttackResourceLevelOv? = null,
    override val primaryMotivation: AttackMotivationOv? = null,
    override val secondaryMotivations: AttackMotivations? = null,
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
    IntrusionSetSdo {

    companion object{
        val stixType = StixType("intrusion-set")
    }

}