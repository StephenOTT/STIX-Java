package com.stephenott.stix.sdo.objects

import com.stephenott.stix.sdo.StixDomainObject
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.InfrastructureTypes
import com.stephenott.stix.type.vocab.KillChainPhases

interface InfrastructureSdo : StixDomainObject {
    val name: String
    val description: String?
    val infrastructureTypes: InfrastructureTypes
    val aliases: StixStringList?
    val killChainPhases: KillChainPhases?
    val firstSeen: StixInstant?
    val lastSeen: StixInstant?
}

data class Infrastructure
    (
    override val name: String,
    override val description: String? = null,
    override val infrastructureTypes: InfrastructureTypes,
    override val aliases: StixStringList? = null,
    override val killChainPhases: KillChainPhases? = null,
    override val firstSeen: StixInstant? = null,
    override val lastSeen: StixInstant? = null,
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
    InfrastructureSdo {

    companion object{
        val stixType = StixType("infrastructure")
    }

}