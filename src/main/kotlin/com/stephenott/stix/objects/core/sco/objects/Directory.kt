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

interface DirectorySco : StixCyberObservableObject {

    val path: String
    val pathEnc: String? //@TODO add validation
    val ctime: StixTimestamp?
    val mtime: StixTimestamp?
    val atime: StixTimestamp?
    val containsRefs: StixIdentifiers?

    companion object :
        CompanionStixType,
        BusinessRulesValidator<DirectorySco>,
        CompanionIdContributingProperties<DirectorySco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf()

        override val stixType = StixType("directory")

        override val idContributingProperties: List<KProperty1<DirectorySco, Any?>> = listOf(
            DirectorySco::path
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override fun objectValidationRules(obj: DirectorySco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

            obj.containsRefs?.let {
                require(it.all { id -> id.type == stixType || id.type == FileSco.stixType },
                    lazyMessage = { "contains_refs must only contain SCOs of type Directory and File." }
                )
            }
        }
    }
}

data class Directory(
        override val path: String,
        override val pathEnc: String? = null,
        override val ctime: StixTimestamp? = null,
        override val mtime: StixTimestamp? = null,
        override val atime: StixTimestamp? = null,
        override val containsRefs: StixIdentifiers?,
        override val type: StixType = StixType(DirectorySco.stixType),
        override val id: StixIdentifier = StixIdentifier(type),
        override val objectMarkingsRefs: String? = null,
        override val granularMarkings: String? = null,
        override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
        override val extensions: Extensions? = null,
        override val defanged: StixBoolean = StixBoolean(),
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : DirectorySco {

    init {
        if (this.stixValidateOnConstruction) {
            DirectorySco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}