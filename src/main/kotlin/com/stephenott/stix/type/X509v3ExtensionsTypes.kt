package com.stephenott.stix.type

import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

class X509v3ExtensionsTypes(private val types: List<X509v3ExtensionsType>): List<X509v3ExtensionsType> by types{
    init {
        require(types.isNotEmpty())
    }
}

data class X509v3ExtensionsType(
        val basicConstraints: String?,
        val nameConstraints: String?,
        val policyConstraints: String?,
        val keyUsage: String?,
        val extendedKeyUsage: String?,
        val subjectKeyIdentifier: String?,
        val authorityKeyIdentifier: String?,
        val subjectAlternativeName: String?,
        val issuerAlternativeName: String?,
        val subjectDirectoryAttributes: String?,
        val crlDistributionPoints: String?,
        val inhibitAnyPolicy: String?,
        val privateKeyUsagePeriodNotBefore: StixTimestamp?,
        val privateKeyUsagePeriodNotAfter: StixTimestamp?,
        val certificatePolicies: String?,
        val policyMappings: String?
) {

    init {
        require(this::class.memberProperties
            .filter {it.visibility == KVisibility.PUBLIC}
            .any { it.getter.call() != null },
            lazyMessage = {"At least one property must be provided/not null."})
    }

}