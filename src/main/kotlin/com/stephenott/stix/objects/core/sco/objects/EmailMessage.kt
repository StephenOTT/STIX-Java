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

interface EmailMessageSco : StixCyberObservableObject {

    val isMultipart: StixBoolean
    val date: StixTimestamp?
    val contentType: String?
    val fromRef: StixIdentifier?
    val senderRef: StixIdentifier?
    val toRefs: StixIdentifiers?
    val ccRefs: StixIdentifiers?
    val bccRefs: StixIdentifiers?
    val messageId: String?
    val subject: String?
    val receivedLines: StixStringList?
    val additionalHeaderFields: AdditionalHeaderFieldsDictionary?
    val body: String?
    val bodyMultipart: MimePartTypes?
    val rawEmailRef: StixIdentifier?

    companion object :
        CompanionStixType,
        BusinessRulesValidator<EmailMessageSco>,
        CompanionIdContributingProperties<EmailMessageSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf()

        override val stixType = StixType("email-message")

        override val idContributingProperties: List<KProperty1<EmailMessageSco, Any?>> = listOf(
            EmailMessageSco::fromRef,
            EmailMessageSco::subject,
            EmailMessageSco::body
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override fun objectValidationRules(obj: EmailMessageSco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

            obj.fromRef?.let {
                require(it.type == EmailAddressSco.stixType,
                    lazyMessage = { "from_ref must be references to email-address SCO" })
            }
            obj.senderRef?.let {
                require(it.type == EmailAddressSco.stixType,
                    lazyMessage = { "sender_ref must be references to email-address SCO" })
            }
            obj.toRefs?.let {
                require(it.all { id -> id.type == EmailAddressSco.stixType },
                    lazyMessage = { "to_refs must be references to email-address SCO" })
            }
            obj.ccRefs?.let {
                require(it.all { id -> id.type == EmailAddressSco.stixType },
                    lazyMessage = { "cc_refs must be references to email-address SCO" })
            }
            obj.bccRefs?.let {
                require(it.all { id -> id.type == EmailAddressSco.stixType },
                    lazyMessage = { "bcc_refs must be references to email-address SCO" })
            }

            if (obj.isMultipart.value) {
                require(obj.body == null,
                    lazyMessage = { "body cannot be used when is_multipart is true" })
            } else {
                require(obj.bodyMultipart == null,
                    lazyMessage = { "body_multipart cannot be used when is_multipart is false" })
            }
        }
    }
}

data class EmailMessage(
        override val isMultipart: StixBoolean,
        override val date: StixTimestamp? = null,
        override val contentType: String? = null,
        override val fromRef: StixIdentifier? = null,
        override val senderRef: StixIdentifier? = null,
        override val toRefs: StixIdentifiers? = null,
        override val ccRefs: StixIdentifiers? = null,
        override val bccRefs: StixIdentifiers? = null,
        override val messageId: String? = null,
        override val subject: String? = null,
        override val receivedLines: StixStringList? = null,
        override val additionalHeaderFields: AdditionalHeaderFieldsDictionary? = null,
        override val body: String? = null,
        override val bodyMultipart: MimePartTypes? = null,
        override val rawEmailRef: StixIdentifier? = null,
        override val type: StixType = StixType(EmailMessageSco.stixType),
        override val id: StixIdentifier = StixIdentifier(type),
        override val objectMarkingsRefs: String? = null,
        override val granularMarkings: String? = null,
        override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
        override val extensions: Extensions? = null,
        override val defanged: StixBoolean = StixBoolean(),
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : EmailMessageSco {

    init {
        if (this.stixValidateOnConstruction) {
            EmailMessageSco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}