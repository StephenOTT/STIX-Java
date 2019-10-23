package com.stephenott.stix

import com.fasterxml.jackson.module.kotlin.readValue
import com.stephenott.stix.objects.StixObject
import com.stephenott.stix.objects.core.StixCoreObject
import com.stephenott.stix.objects.core.sco.objects.IPv6Address
import com.stephenott.stix.objects.core.sco.objects.NetworkTraffic
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sdo.objects.AttackPattern
import com.stephenott.stix.objects.core.sro.objects.Relationship
import com.stephenott.stix.serialization.json.createStixMapper
import com.stephenott.stix.objects.core.sdo.StixDomainObject.*
import com.stephenott.stix.objects.core.sdo.objects.AttackPatternSdo
import com.stephenott.stix.objects.core.sdo.objects.ObservedData
import com.stephenott.stix.objects.core.sro.StixRelationshipObject
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.objects.core.sro.objects.Sighting
import com.stephenott.stix.objects.meta.StixMetaObject
import com.stephenott.stix.serialization.json.StixContentMapper
import com.stephenott.stix.serialization.json.toJson
import com.stephenott.stix.type.*
import java.time.Instant

object MainRunner {

    @JvmStatic
    fun main(args: Array<String>){

        val ap1 = AttackPattern("124")
        val ap2 = AttackPattern("1245")
        val ip6 = IPv6Address("dog")

        val sighting1 = Sighting(sightingOfRef = ap1.id)
        val net1 = NetworkTraffic(isActive = StixBoolean(true), protocols = StixStringList(listOf("http")))

        val mapper = StixContentMapper()

        println(net1.toJson(mapper))
        println(mapper.parseJson<NetworkTraffic>(net1.toJson(mapper)))

        val jsonString: String = ap1.toJson(mapper)
        println(jsonString)

        println(mapper.parseJson<StixContent>(jsonString))
        println(mapper.parseJson<StixObject>(jsonString))
        println(mapper.parseJson<StixCoreObject>(jsonString))
        println(mapper.parseJson<StixDomainObject>(jsonString))
        println(mapper.parseJson<AttackPatternSdo>(jsonString))
        println(mapper.parseJson<AttackPattern>(jsonString))


        val rel = Relationship(
            relationshipType = RelationshipType("duplicate-of"),
            sourceRef = ap1,
            targetRef = ap2
        )
        println(mapper.parseJson<StixContent>(rel.toJson(mapper)))
        println(mapper.parseJson<StixObject>(rel.toJson(mapper)))
        println(mapper.parseJson<StixCoreObject>(rel.toJson(mapper)))
        println(mapper.parseJson<StixRelationshipObject>(rel.toJson(mapper)))
        println(mapper.parseJson<RelationshipSro>(rel.toJson(mapper)))
        println(mapper.parseJson<Relationship>(rel.toJson(mapper)))

        println(mapper.parseJson<Sighting>(sighting1.toJson(mapper)))
    }
}