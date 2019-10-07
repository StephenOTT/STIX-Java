package com.stephenott.stix.sdo.objects

import com.stephenott.stix.sdo.StixDomainObject
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.*

interface ThreatActorSdo : StixDomainObject {
    val name: String
    val description: String?
    val threatActorTypes: ThreatActorTypes
    val aliases: StixStringList?
    val firstSeen: StixInstant?
    val lastSeen: StixInstant?
    val roles: ThreatActorRoles?
    val goals: StixStringList?
    val sophistication: ThreatActorSophisticationOv?
    val resourceLevel: AttackResourceLevelOv?
    val primaryMotivation: AttackMotivationOv?
    val secondaryMotivation: AttackMotivationOv?
    val personalMotivations: AttackMotivations?
}

data class ThreatActor(
    override val name: String,
    override val description: String? = null,
    override val threatActorTypes: ThreatActorTypes,
    override val aliases: StixStringList? = null,
    override val firstSeen: StixInstant? = null,
    override val lastSeen: StixInstant? = null,
    override val roles: ThreatActorRoles? = null,
    override val goals: StixStringList? = null,
    override val sophistication: ThreatActorSophisticationOv? = null,
    override val resourceLevel: AttackResourceLevelOv? = null,
    override val primaryMotivation: AttackMotivationOv? = null,
    override val secondaryMotivation: AttackMotivationOv? = null,
    override val personalMotivations: AttackMotivations? = null,
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
    ThreatActorSdo {

    companion object{
        val stixType = StixType("threat-actor")
    }

}