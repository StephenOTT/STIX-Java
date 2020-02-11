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
    val created: StixTimestamp
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

interface StixLabelsProp {
    val labels: StixLabels?
}

interface StixModifiedProp {
    val modified: StixTimestamp
}

interface StixRevokedProp {
    val revoked: StixBoolean
}

interface StixConfidenceProp {
    val confidence: StixConfidence?
}

interface StixLangProp {
    val lang: StixLang?
}

/**
 * Only used on SCO
 */
interface StixExtensionsProp {
    val extensions: Extensions?
}

/**
 * Only used on SCO
 */
interface StixDefangedProp {
    val defanged: StixBoolean
}