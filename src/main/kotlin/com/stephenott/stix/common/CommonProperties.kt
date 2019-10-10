package com.stephenott.stix.common

import com.stephenott.stix.type.*
import com.stephenott.stix.type.StixConfidence
import com.stephenott.stix.type.StixLabels
import com.stephenott.stix.type.StixLang

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

interface StixConfidence {
    val confidence: StixConfidence
}

interface StixLang {
    val lang: StixLang
}