package com.stephenott.stix.sdo.objects

import com.stephenott.stix.sdo.StixDomainObject
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.KillChainPhases
import com.stephenott.stix.type.vocab.ToolTypes

interface ToolSdo : StixDomainObject {
    val name: String
    val description: String?
    val toolTypes: ToolTypes
    val aliases: StixStringList?
    val killChainPhases: KillChainPhases?
    val toolVersion: String?
}

data class Tool(
    override val name: String,
    override val description: String? = null,
    override val toolTypes: ToolTypes,
    override val aliases: StixStringList? = null,
    override val killChainPhases: KillChainPhases? = null,
    override val toolVersion: String? = null,
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
    ToolSdo {

    companion object{
        val stixType = StixType("tool")
    }

}