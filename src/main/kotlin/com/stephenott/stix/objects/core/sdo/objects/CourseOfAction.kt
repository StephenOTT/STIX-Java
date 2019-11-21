package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.common.requireStixType
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.CourseOfActionTypeOv

interface CourseOfActionSdo : StixDomainObject {
    val name: String
    val description: String?
    val actionType: CourseOfActionTypeOv? //@TODO add option to override type value as the spec says it's a "should"
    val osExecutionEnvs: OsExecutionEnvs?
    val actionBin: StixBinary?
    val actionReference: ExternalReference?

    companion object: CompanionStixType,
        BusinessRulesValidator<CourseOfActionSdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("course-of-action")

        override fun objectValidationRules(obj: CourseOfActionSdo, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

            if (obj.actionReference != null){
                require(obj.actionBin == null,
                    lazyMessage = {"action_bin must not be present if action_reference is provided."})
            }
            if (obj.actionBin != null){
                require(obj.actionReference == null,
                    lazyMessage = {"action_reference must not be present if action_bin is provided."})
            }
        }

        override val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                CourseOfActionSdo::class,
                RelationshipType("investigates"),
                IndicatorSdo::class
            ),

            AllowedRelationship(
                CourseOfActionSdo::class,
                RelationshipType("mitigates"),
                AttackPatternSdo::class
            ),
            AllowedRelationship(
                CourseOfActionSdo::class,
                RelationshipType("mitigates"),
                IndicatorSdo::class
            ),
            AllowedRelationship(
                CourseOfActionSdo::class,
                RelationshipType("mitigates"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                CourseOfActionSdo::class,
                RelationshipType("mitigates"),
                ToolSdo::class
            ),
            AllowedRelationship(
                CourseOfActionSdo::class,
                RelationshipType("mitigates"),
                VulnerabilitySdo::class
            ),

            AllowedRelationship(
                CourseOfActionSdo::class,
                RelationshipType("remediates"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                CourseOfActionSdo::class,
                RelationshipType("remediates"),
                VulnerabilitySdo::class
            )
        )
    }


}

data class CourseOfAction(
        override val name: String,
        override val description: String? = null,
        override val actionType: CourseOfActionTypeOv? = null,
        override val osExecutionEnvs: OsExecutionEnvs? = null,
        override val actionBin: StixBinary? = null,
        override val actionReference: ExternalReference? = null,
        override val type: StixType = CourseOfActionSdo.stixType,
        override val id: StixIdentifier = StixIdentifier(type),
        override val createdByRef: String? = null,
        override val created: StixTimestamp = StixTimestamp(),
        override val externalReferences: ExternalReferences? = null,
        override val objectMarkingsRefs: String? = null,
        override val granularMarkings: String? = null,
        override val specVersion: StixSpecVersion = StixSpecVersion(),
        override val labels: StixLabels? = null,
        override val modified: StixTimestamp = StixTimestamp(created),
        override val revoked: StixBoolean = StixBoolean(),
        override val confidence: StixConfidence? = null,
        override val lang: StixLang? = null,
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : CourseOfActionSdo {

    init {
        if (this.stixValidateOnConstruction) {
            CourseOfActionSdo.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return CourseOfActionSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}