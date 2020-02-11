package com.stephenott.stix.objects.core.sco.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.common.*
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.objects.core.sco.extension.objects.WindowsProcessExtensionExt
import com.stephenott.stix.objects.core.sco.extension.objects.WindowsServiceExtensionExt
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.*
import com.stephenott.stix.type.StixSpecVersion.Companion.StixVersions
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

interface ProcessSco : StixCyberObservableObject {

    val isHidden: StixBoolean?
    val pid: StixInteger?
    val createdTime: StixTimestamp?
    val cwd: String?
    val commandLine: String?
    val environmentVariables: LinkedHashMap<String, String>? //@TODO Refactor: https://github.com/oasis-tcs/cti-stix2/issues/185#issuecomment-543299610
    val openedConnectionRef: StixIdentifier?
    val creatorUserRef: StixIdentifier?
    val imageRef: StixIdentifier?
    val parentRef: StixIdentifier?
    val childRefs: StixIdentifiers?

    companion object :
        CompanionStixType,
        BusinessRulesValidator<ProcessSco>,
        CompanionIdContributingProperties<ProcessSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val stixType = StixType("process")

        override val idContributingProperties: List<KProperty1<ProcessSco, Any?>> = listOf(
            //@TODO review as there are no ID contributing props as per current spec
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf(
            WindowsProcessExtensionExt::class,
            WindowsServiceExtensionExt::class
        )

        override fun objectValidationRules(obj: ProcessSco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

            require(obj.openedConnectionRef?.type == NetworkTrafficSco.stixType,
                lazyMessage = { "opened_connection_ref must only reference network-traffic SCO." })

            require(obj.creatorUserRef?.type == UserAccountSco.stixType,
                lazyMessage = { "creator_user_ref must only reference user-account SCO." })

            require(obj.imageRef?.type == FileSco.stixType,
                lazyMessage = { "image_ref must only reference file SCO." })

            require(obj.parentRef?.type == ProcessSco.stixType,
                lazyMessage = { "parent_ref must only reference process SCO." })

            require(obj.childRefs?.all { it.type == ProcessSco.stixType }!!,
                lazyMessage = { "child_refs must only have values that reference process SCO." })
        }

    }
}

data class Process(
        override val isHidden: StixBoolean? = null,
        override val pid: StixInteger? = null,
        override val createdTime: StixTimestamp? = null,
        override val cwd: String? = null,
        override val commandLine: String? = null,
        override val environmentVariables: LinkedHashMap<String, String>? = null,
        override val openedConnectionRef: StixIdentifier? = null,
        override val creatorUserRef: StixIdentifier? = null,
        override val imageRef: StixIdentifier? = null,
        override val parentRef: StixIdentifier? = null,
        override val childRefs: StixIdentifiers? = null,
        override val type: StixType = StixType(ProcessSco.stixType),
        override val id: StixIdentifier = StixIdentifier(type), //@TODO review as spec currently says that a Process SCO uses a UUID v4
        override val objectMarkingsRefs: String? = null,
        override val granularMarkings: String? = null,
        override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
        override val extensions: Extensions? = null,
        override val defanged: StixBoolean = StixBoolean(),
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : ProcessSco {

    init {
        if (this.stixValidateOnConstruction) {
            ProcessSco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}