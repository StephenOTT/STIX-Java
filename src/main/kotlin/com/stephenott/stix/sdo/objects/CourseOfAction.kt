package com.stephenott.stix.sdo.objects

import com.stephenott.stix.sdo.StixDomainObject
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.CourseOfActionTypeOv

interface CourseOfActionSdo : StixDomainObject {
    val name: String
    val description: String?
    val actionType: CourseOfActionTypeOv?
    val osExecutionEnvs: OsExecutionEnvs?
    val actionBin: StixBinary?
    val actionReference: ExternalReference?
}

data class CourseOfAction
    (
    override val name: String,
    override val description: String? = null,
    override val actionType: CourseOfActionTypeOv? = null,
    override val osExecutionEnvs: OsExecutionEnvs? = null,
    override val actionBin: StixBinary? = null,
    override val actionReference: ExternalReference? = null,
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
) : CourseOfActionSdo {

    companion object {
        val stixType = StixType("course-of-action")
    }
}