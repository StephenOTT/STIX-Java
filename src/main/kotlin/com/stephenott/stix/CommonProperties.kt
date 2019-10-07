package com.stephenott.stix

import com.stephenott.stix.type.*
import com.stephenott.stix.type.StixLabels

interface StixTypeProp{
    val type: StixType
}

interface StixIdentifierProp{
    val id: StixIdentifier
}

interface StixCreatedByRef{
    val createdByRef: String?
}

interface StixCreatedProp{
    val created: StixInstant
}

interface StixExternalReferencesProp{
    val externalReferences: ExternalReferences?
}

interface StixObjectMarkingsRefsProp{
    val objectMarkingsRefs: String?
}

interface StixGranularMarkingsProp{
    val granularMarkings: String?
}

interface StixSpecVersionProp{
    val specVersion: StixSpecVersion
}

interface StixLabels {
    val labels: StixLabels?
}

interface StixModified {
    val modified: StixInstant
}

interface StixRevoked {
    val revoked: StixBoolean
}