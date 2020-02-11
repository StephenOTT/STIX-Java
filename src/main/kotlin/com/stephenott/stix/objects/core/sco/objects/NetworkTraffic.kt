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

interface NetworkTrafficSco : StixCyberObservableObject {

    val start: StixTimestamp?
    val end: StixTimestamp?
    val isActive: StixBoolean?
    val srcRef: StixIdentifier?
    val dstRef: StixIdentifier?
    val srcPort: StixInteger?
    val dstPort: StixInteger?
    val protocols: StixStringList
    val srcByteCount: StixInteger?
    val dstByteCount: StixInteger?
    val srcPackets: StixInteger?
    val dstPackets: StixInteger?
    val ipfix: IpfixDictionary?
    val srcPayloadRef: StixIdentifier?
    val dstPayloadRef: StixIdentifier?
    val encapsulatesRefs: StixIdentifiers?
    val encapsulatedByRef: StixIdentifier?

    companion object :
        CompanionStixType,
        BusinessRulesValidator<NetworkTrafficSco>,
        CompanionIdContributingProperties<NetworkTrafficSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val stixType = StixType("network-traffic")

        override val idContributingProperties: List<KProperty1<NetworkTrafficSco, Any?>> = listOf(
            NetworkTrafficSco::start,
            NetworkTrafficSco::srcRef,
            NetworkTrafficSco::dstRef,
            NetworkTrafficSco::srcPort,
            NetworkTrafficSco::dstPort,
            NetworkTrafficSco::protocols
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf(
            HttpRequestExtensionExt::class,
            TcpExtensionExt::class,
            IcmpExtensionExt::class,
            NetworkSocketExtensionExt::class
        )

        override fun objectValidationRules(obj: NetworkTrafficSco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

            obj.isActive?.let {
                if (it.value) {
                    require(obj.end == null,
                        lazyMessage = { "If is_active is true then end must not be included." })
                }
            }
            if (obj.start != null && obj.end != null) {
                require(obj.end?.instant!!.isAfter(obj.start?.instant),
                    lazyMessage = { "if start and end are both defined then end must be later than start." })
            }
            obj.end?.let {
                require(obj.isActive != null &&
                        !obj.isActive!!.value &&
                        obj.isActive!!.isDefinedValue,
                    lazyMessage = { "If end is provided then is_active must be provided and have a value of false." })
            }
            obj.srcRef?.let {
                require(it.type in listOf(
                    IPv4AddressSco.stixType,
                    IPv6AddressSco.stixType,
                    MacAddressSco.stixType,
                    DomainNameSco.stixType
                ),
                    lazyMessage = { "src_ref must be a reference to one of: ipv4-addr, ipv6-addr, mac-addr, domain-name." })
            }
            obj.dstRef?.let {
                require(it.type in listOf(
                    IPv4AddressSco.stixType,
                    IPv6AddressSco.stixType,
                    MacAddressSco.stixType,
                    DomainNameSco.stixType
                ),
                    lazyMessage = { "dst_ref must be a reference to one of: ipv4-addr, ipv6-addr, mac-addr, domain-name." })
            }
            obj.srcPort?.let {
                require(it.value in 0..65535,
                    lazyMessage = { "src_port must be in range 0 to 65535" })
            }
            obj.dstPort?.let {
                require(it.value in 0..65535,
                    lazyMessage = { "dst_port must be in range 0 to 65535" })
            }
            obj.srcPayloadRef?.let {
                require(it.type == ArtifactSco.stixType,
                    lazyMessage = { "src_payload_ref must only reference type artifact" })
            }
            obj.dstPayloadRef?.let {
                require(it.type == ArtifactSco.stixType,
                    lazyMessage = { "dst_payload_ref must only reference type artifact" })
            }
            obj.encapsulatesRefs?.let {
                require(it.all { id -> id.type == NetworkTrafficSco.stixType },
                    lazyMessage = { "encapsulates_refs values must only reference type network-traffic" })
            }
            obj.encapsulatedByRef?.let {
                require(it.type == NetworkTrafficSco.stixType,
                    lazyMessage = { "encapsulated_by_ref must only reference type network-traffic" })
            }
        }

    }
}

data class NetworkTraffic(
        override val start: StixTimestamp? = null,
        override val end: StixTimestamp? = null,
        override val isActive: StixBoolean? = null,
        override val srcRef: StixIdentifier? = null,
        override val dstRef: StixIdentifier? = null,
        override val srcPort: StixInteger? = null,
        override val dstPort: StixInteger? = null,
        override val protocols: StixStringList,
        override val srcByteCount: StixInteger? = null,
        override val dstByteCount: StixInteger? = null,
        override val srcPackets: StixInteger? = null,
        override val dstPackets: StixInteger? = null,
        override val ipfix: IpfixDictionary? = null,
        override val srcPayloadRef: StixIdentifier? = null,
        override val dstPayloadRef: StixIdentifier? = null,
        override val encapsulatesRefs: StixIdentifiers? = null,
        override val encapsulatedByRef: StixIdentifier? = null,
        override val type: StixType = StixType(NetworkTrafficSco.stixType),
        override val id: StixIdentifier = StixIdentifier(type),
        override val objectMarkingsRefs: String? = null,
        override val granularMarkings: String? = null,
        override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
        override val extensions: Extensions? = null,
        override val defanged: StixBoolean = StixBoolean(),
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : NetworkTrafficSco {

    init {
        if (this.stixValidateOnConstruction) {
            NetworkTrafficSco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}