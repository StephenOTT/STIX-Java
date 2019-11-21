package com.stephenott.stix.objects.core.sco.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.common.*
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.objects.core.sco.extension.objects.*
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.*
import com.stephenott.stix.type.StixSpecVersion.Companion.StixVersions
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.isSubclassOf

interface FileSco : StixCyberObservableObject {

    val hashes: HashesDictionary?
    val size: StixInteger?
    val name: String?
    val nameEnc: String?
    val magicNumberHex: StixHex?
    val mimeType: String?
    val cTime: StixTimestamp?
    val mTime: StixTimestamp?
    val aTime: StixTimestamp?
    val parentDirectoryRef: StixIdentifier?
    val containsRefs: StixIdentifiers?
    val contentRef: StixIdentifier?

    companion object :
        CompanionStixType,
        BusinessRulesValidator<FileSco>,
        CompanionIdContributingProperties<FileSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val stixType = StixType("file")

        override val idContributingProperties: List<KProperty1<FileSco, Any?>> = listOf(
            FileSco::hashes,
            FileSco::name,
            FileSco::extensions
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf(
            NtfsFileExtensionExt::class,
            RasterImageFileExtensionExt::class,
            PdfFileExtensionExt::class,
            ArchiveFileExtensionExt::class,
            WindowsPeBinaryFileExtensionExt::class
        )

        override fun objectValidationRules(obj: FileSco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

            obj.size?.let {
                require(it.value >= 0,
                    lazyMessage = { "size must not be a negative number." })
            }
            obj.parentDirectoryRef?.let {
                require(it.type == DirectorySco.stixType,
                    lazyMessage = { "parent_directory_ref must only contain a reference to a Directory object SCO." })
            }
            obj.containsRefs?.let {
                require(it.all { id -> stixInstance.registries.objectRegistry.registry.getValue(id.type).isSubclassOf(StixCyberObservableObject::class) },
                    lazyMessage = { "contains_refs can only contain references to SCOs." })
            }
            obj.contentRef?.let {
                require(it.type == ArtifactSco.stixType,
                    lazyMessage = { "content_ref must only contain a reference to a Artifact SCO." })
            }
        }
    }
}

data class File(
        override val hashes: HashesDictionary? = null,
        override val size: StixInteger? = null,
        override val name: String? = null,
        override val nameEnc: String? = null,
        override val magicNumberHex: StixHex? = null,
        override val mimeType: String? = null,
        override val cTime: StixTimestamp? = null,
        override val mTime: StixTimestamp? = null,
        override val aTime: StixTimestamp? = null,
        override val parentDirectoryRef: StixIdentifier? = null,
        override val containsRefs: StixIdentifiers? = null,
        override val contentRef: StixIdentifier? = null,
        override val type: StixType = StixType(FileSco.stixType),
        override val id: StixIdentifier = StixIdentifier(type),
        override val objectMarkingsRefs: String? = null,
        override val granularMarkings: String? = null,
        override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
        override val extensions: Extensions? = null,
        override val defanged: StixBoolean = StixBoolean(),
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : FileSco {

    init {
        if (this.stixValidateOnConstruction) {
            FileSco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}