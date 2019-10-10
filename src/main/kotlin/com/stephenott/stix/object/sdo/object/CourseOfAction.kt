package com.stephenott.stix.`object`.sdo.`object`

import com.stephenott.stix.`object`.sdo.StixDomainObject
import com.stephenott.stix.`object`.sro.`object`.AllowedRelationship
import com.stephenott.stix.`object`.sro.`object`.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.CourseOfActionTypeOv

interface CourseOfActionSdo : StixDomainObject {
    val name: String
    val description: String?
    val actionType: CourseOfActionTypeOv?
    val osExecutionEnvs: OsExecutionEnvs?
    val actionBin: StixBinary?
    val actionReference: ExternalReference?

    companion object {
        val stixType = StixType("course-of-action")

        val allowedRelationships: List<AllowedRelationship> = listOf(
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
    override val created: StixInstant = StixInstant(),
    override val externalReferences: ExternalReferences? = null,
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(),
    override val labels: StixLabels? = null,
    override val modified: StixInstant = StixInstant(created),
    override val revoked: StixBoolean = StixBoolean()
) : CourseOfActionSdo {

    override fun allowedRelationships(): List<AllowedRelationship> {
        return CourseOfActionSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}