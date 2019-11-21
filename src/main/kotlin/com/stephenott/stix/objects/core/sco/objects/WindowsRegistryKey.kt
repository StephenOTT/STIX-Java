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

interface WindowsRegistryKeySco : StixCyberObservableObject {

    val key: String?
    val values: WindowsRegistryValueTypes?
    val modifiedTimed: StixTimestamp?
    val creatorUserRef: StixIdentifier?
    val numberOfSubkeys: StixInteger?

    companion object :
        CompanionStixType,
        BusinessRulesValidator<WindowsRegistryKeySco>,
        CompanionIdContributingProperties<WindowsRegistryKeySco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val stixType = StixType("windows-registry-key")

        override val idContributingProperties: List<KProperty1<WindowsRegistryKeySco, Any?>> = listOf(
            WindowsRegistryKeySco::key,
            WindowsRegistryKeySco::values // @TODO ** There is another pattern like this where only 1 value from this prop should be used in the ID.  Should look to standardize on this to make the code simpler
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf()

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf()

        override fun objectValidationRules(obj: WindowsRegistryKeySco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)
            require(
                listOf(obj.key, obj.values, obj.modifiedTimed, obj.creatorUserRef, obj.numberOfSubkeys)
                    .any { it != null },
                lazyMessage = { "windows-registry-key requires at least one property must be included." }
            )

            require(obj.creatorUserRef?.type == UserAccountSco.stixType,
                lazyMessage = { "creator_user_ref must only reference a user-account SCO" }
            )
        }

    }
}

data class WindowsRegistryKey(
        override val key: String? = null,
        override val values: WindowsRegistryValueTypes? = null,
        override val modifiedTimed: StixTimestamp? = null,
        override val creatorUserRef: StixIdentifier? = null,
        override val numberOfSubkeys: StixInteger? = null,
        override val type: StixType = StixType(WindowsRegistryKeySco.stixType),
        override val id: StixIdentifier = StixIdentifier(type),
        override val objectMarkingsRefs: String? = null,
        override val granularMarkings: String? = null,
        override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
        override val extensions: Extensions? = null,
        override val defanged: StixBoolean = StixBoolean(),
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : WindowsRegistryKeySco {

    init {
        if (this.stixValidateOnConstruction) {
            WindowsRegistryKeySco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}