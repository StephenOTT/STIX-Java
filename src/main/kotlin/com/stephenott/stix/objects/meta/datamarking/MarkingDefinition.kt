package com.stephenott.stix.objects.meta.datamarking

import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.MarkingDefinitionTypeOv

interface MarkingDefinitionDm: DataMarking{
    val name: String?
    val definitionType: MarkingDefinitionTypeOv
    val definition: MarkingObject

    companion object {
        val stixType = StixType("marking-definition")
    }
}

data class MarkingDefinition(
    override val name: String? = null,
    override val definitionType: MarkingDefinitionTypeOv,
    override val definition: MarkingObject,
    override val type: StixType = MarkingDefinitionDm.stixType,
    override val id: StixIdentifier = StixIdentifier(type),
    override val createdByRef: String? = null,
    override val created: StixInstant = StixInstant(),
    override val externalReferences: ExternalReferences? = null,
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion()

): MarkingDefinitionDm {

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}