package com.stephenott.stix.objects.core.sco.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.common.*
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.*
import com.stephenott.stix.type.StixSpecVersion.Companion.StixVersions
import com.stephenott.stix.type.vocab.EncryptionAlgorithmEnum
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

interface ArtifactSco : StixCyberObservableObject {

    val mimeType: String?
    val payloadBin: StixBinary?
    val url: String?
    val hashes: HashesDictionary?
    val encryptionAlgorithm: EncryptionAlgorithmEnum?
    val decryptionKey: String?

    companion object :
        CompanionStixType,
        BusinessRulesValidator<ArtifactSco>,
        CompanionIdContributingProperties<ArtifactSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf()

        override val stixType = StixType("artifact")

        override val idContributingProperties: List<KProperty1<ArtifactSco, Any?>> = listOf(
            ArtifactSco::hashes,
            ArtifactSco::payloadBin
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override fun objectValidationRules(obj: ArtifactSco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

            if (obj.url != null) {
                require(obj.payloadBin == null, lazyMessage = { "payload_bin must not be present if url is provided." })
            }
            if (obj.payloadBin != null) {
                require(obj.url == null, lazyMessage = { "url must not be present if payload_bin is provided." })
            }
            if (obj.url != null) {
                require(obj.hashes != null, lazyMessage = { "hashes must be present when url property is present" })
            }
            if (obj.encryptionAlgorithm == null) {
                require(
                    obj.decryptionKey == null,
                    lazyMessage = { "decryption_key must not be present when encryption_algorithm is absent." })
            }
        }

    }
}

data class Artifact(
    override val mimeType: String? = null,
    override val payloadBin: StixBinary? = null,
    override val url: String? = null,
    override val hashes: HashesDictionary? = null,
    override val encryptionAlgorithm: EncryptionAlgorithmEnum? = null,
    override val decryptionKey: String? = null,
    override val type: StixType = StixType(ArtifactSco.stixType),
    override val id: StixIdentifier = StixIdentifier(type),
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
    override val extensions: Extensions? = null,
    override val defanged: StixBoolean = StixBoolean(),
    override val stixInstance: Stix = Stix.defaultStixInstance,
    override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : ArtifactSco {

    init {
        if (this.stixValidateOnConstruction) {
            ArtifactSco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}