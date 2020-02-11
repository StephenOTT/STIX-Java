package com.stephenott.stix.common

import com.stephenott.stix.Stix
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.StixType
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

interface BusinessRulesValidator<in T>{
    fun objectValidationRules(obj: T, stixInstance: Stix)
}

interface BusinessRulesExtensionValidator<in T>{
    fun objectValidationRules(obj: T)
}

interface CompanionStixType{
    val stixType: StixType
}

interface CompanionExtensionType{
    val extensionType: String
}

interface CompanionIdContributingProperties<T>{
    val idContributingProperties: List<KProperty1<T, Any?>>
}

interface CompanionAllowedRelationships{
    val allowedRelationships: List<AllowedRelationship>
}

interface CompanionAllowedExtensions{
    val allowedExtensions: List<KClass<out ScoExtension>>
}