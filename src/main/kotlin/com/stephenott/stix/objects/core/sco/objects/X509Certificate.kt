package com.stephenott.stix.objects.core.sco.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.common.*
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.*
import com.stephenott.stix.type.StixSpecVersion.Companion.StixVersions
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

interface X509CertificateSco : StixCyberObservableObject {

    val isSelfSigned: StixBoolean?
    val hashes: HashesDictionary?
    val version: String?
    val serialNumber: String?
    val signatureAlgorithm: String?
    val issuer: String?
    val validityNotBefore: StixTimestamp?
    val validityNotAfter: StixTimestamp?
    val subject: String?
    val subjectPublicKeyAlgorithm: String?
    val subjectPublicKeyModulus: String?
    val subjectPublicKeyExponent: StixInteger?
    val x509v3Extensions: X509v3ExtensionsTypes?

    companion object :
        CompanionStixType,
        BusinessRulesValidator<X509CertificateSco>,
        CompanionIdContributingProperties<X509CertificateSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val stixType = StixType("x509-certificate")

        override val idContributingProperties: List<KProperty1<X509CertificateSco, Any?>> = listOf(
            X509CertificateSco::hashes, //@TODO If the ​hashes​ property is present, include only one hash. The selected hash ​SHOULD​ come from this ordered list (based on the following order of preference) [ MD5, SHA-1, SHA-256, SHA-512 ].
            X509CertificateSco::serialNumber
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf()

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf()

        override fun objectValidationRules(obj: X509CertificateSco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

            require(listOf( //@TODO review against Stix 2 Issues against 182
                obj.isSelfSigned,
                obj.hashes,
                obj.version,
                obj.serialNumber,
                obj.signatureAlgorithm,
                obj.issuer,
                obj.validityNotBefore,
                obj.validityNotAfter,
                obj.subject,
                obj.subjectPublicKeyAlgorithm,
                obj.subjectPublicKeyModulus,
                obj.subjectPublicKeyExponent,
                obj.x509v3Extensions
            ).any { it != null },
                lazyMessage = { "X509-Certificate must contain at least one property (other than " })
        }
    }
}

data class X509Certificate(
        override val isSelfSigned: StixBoolean? = null,
        override val hashes: HashesDictionary? = null,
        override val version: String? = null,
        override val serialNumber: String? = null,
        override val signatureAlgorithm: String? = null,
        override val issuer: String? = null,
        override val validityNotBefore: StixTimestamp? = null,
        override val validityNotAfter: StixTimestamp? = null,
        override val subject: String? = null,
        override val subjectPublicKeyAlgorithm: String? = null,
        override val subjectPublicKeyModulus: String? = null,
        override val subjectPublicKeyExponent: StixInteger? = null,
        override val x509v3Extensions: X509v3ExtensionsTypes? = null,
        override val type: StixType = StixType(X509CertificateSco.stixType),
        override val id: StixIdentifier = StixIdentifier(type),
        override val objectMarkingsRefs: String? = null,
        override val granularMarkings: String? = null,
        override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
        override val extensions: Extensions? = null,
        override val defanged: StixBoolean = StixBoolean(),
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : X509CertificateSco {

    init {
        if (this.stixValidateOnConstruction) {
            X509CertificateSco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}