package com.stephenott.stix.objects.meta.datamarking

import com.stephenott.stix.Stix
import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.common.requireStixType
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.MarkingDefinitionTypeOv

interface MarkingDefinitionDm: DataMarking{
    val name: String?
    val definitionType: MarkingDefinitionTypeOv
    val definition: MarkingObject

    companion object: CompanionStixType,
        BusinessRulesValidator<MarkingDefinitionDm>,
        CompanionAllowedRelationships {

        const val definitionPolymorphicFieldName: String = "definition_type"

        override val stixType = StixType("marking-definition")

        override val allowedRelationships: List<AllowedRelationship> = listOf(
            //@TODO review if this is needed for this object
        )

        override fun objectValidationRules(obj: MarkingDefinitionDm, stixInstance: Stix) {
            requireStixType(this.stixType, obj)
        }

    }
}

data class MarkingDefinition(
        override val name: String? = null,
        override val definitionType: MarkingDefinitionTypeOv,
        override val definition: MarkingObject,
        override val type: StixType = MarkingDefinitionDm.stixType,
        override val id: StixIdentifier = StixIdentifier(type),
        override val createdByRef: String? = null,
        override val created: StixTimestamp = StixTimestamp(),
        override val externalReferences: ExternalReferences? = null,
        override val objectMarkingsRefs: String? = null,
        override val granularMarkings: String? = null,
        override val specVersion: StixSpecVersion = StixSpecVersion(),
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
): MarkingDefinitionDm {

    init {
        if (this.stixValidateOnConstruction) {
            MarkingDefinitionDm.objectValidationRules(this, stixInstance)
        }
    }


    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}