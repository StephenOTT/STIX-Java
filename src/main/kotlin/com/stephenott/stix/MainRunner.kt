package com.stephenott.stix

import com.stephenott.stix.objects.core.sco.objects.NetworkTraffic
import com.stephenott.stix.objects.core.sdo.objects.AttackPattern
import com.stephenott.stix.objects.core.sro.objects.Sighting
import com.stephenott.stix.serialization.json.StixJsonContentMapper
import com.stephenott.stix.serialization.json.toJson
import com.stephenott.stix.type.*

object MainRunner {

    @JvmStatic
    fun main(args: Array<String>) {

        // Create a Stix instance (used to provide configurations of supported objects
        val stix1 = Stix()

        // generation of a Attack Pattern using the default configs
        val ap = AttackPattern(name = "124",
                confidence = StixConfidence(33))

        // Generation of a Attack Pattern using the stix1 instance ("trust group")
        // This is used to force a specific stix instance to be used: this would be used to
        // validate/transfer objects between trust groups, ensuring that content is valid
        val ap1: AttackPattern = stix1.create(AttackPattern(name = "124",
                        confidence = StixConfidence(33)))

        val sighting1 = Sighting(sightingOfRef = ap1.id)
        val net1 = NetworkTraffic(isActive = StixBoolean(true), protocols = StixStringList(listOf("http")))

        val mapper = StixJsonContentMapper.fromStixInstance(stix1)
        val dog = "{\"name\":\"124\",\"type\":\"attack-pattern\",\"id\":\"attack-pattern--771e3ada-0db7-42be-b389-0590181f64c4\",\"created\":\"2019-11-19T00:29:54.294Z\",\"spec_version\":\"2.1\",\"modified\":\"2019-11-19T00:29:54.294Z\",\"confidence\":33,\"stixValidateOnConstruction\": \"222\"}"
        println(ap1.toJson(mapper))
        println(mapper.parseJson<AttackPattern>(dog))
//        println(mapper.parseJson<AttackPatternSdo>(ap1.toJson(mapper)))
//        println(mapper.parseJson<StixDomainObject>(ap1.toJson(mapper)))
//        println(mapper.parseJson<StixCoreObject>(ap1.toJson(mapper)))
//        println(mapper.parseJson<StixObject>(ap1.toJson(mapper)))
//        println(mapper.parseJson<StixContent>(ap1.toJson(mapper)))
//
//        println(net1.toJson(mapper))
//        println(mapper.parseJson<NetworkTraffic>(net1.toJson(mapper)))
//
//        val jsonString: String = ap1.toJson(mapper)
//        println(jsonString)
//
//        println(mapper.parseJson<StixContent>(jsonString))
//        println(mapper.parseJson<StixObject>(jsonString))
//        println(mapper.parseJson<StixCoreObject>(jsonString))
//        println(mapper.parseJson<StixDomainObject>(jsonString))
//        println(mapper.parseJson<AttackPatternSdo>(jsonString))
//        println(mapper.parseJson<AttackPattern>(jsonString))
//
//
//        val rel = Relationship(
//            relationshipType = RelationshipType("duplicate-of"),
//            sourceRef = ap1,
//            targetRef = ap2
//        )
//        println(mapper.parseJson<StixContent>(rel.toJson(mapper)))
//        println(mapper.parseJson<StixObject>(rel.toJson(mapper)))
//        println(mapper.parseJson<StixCoreObject>(rel.toJson(mapper)))
//        println(mapper.parseJson<StixRelationshipObject>(rel.toJson(mapper)))
//        println(mapper.parseJson<RelationshipSro>(rel.toJson(mapper)))
//        println(mapper.parseJson<Relationship>(rel.toJson(mapper)))
//
//        println(mapper.parseJson<Sighting>(sighting1.toJson(mapper)))
//
//        val markDef1 = MarkingDefinition("test", MarkingDefinitionTypeOv("tlp"),
//            Tlp("white")
//        )
//        println(mapper.parseJson<MarkingDefinition>(markDef1.toJson(mapper)))
//        println(markDef1.toJson(mapper))
//
//        val markDef2 = MarkingDefinition("test2", MarkingDefinitionTypeOv("statement"),
//            Statement("white-statement")
//        )
//        println(mapper.parseJson<MarkingDefinition>(markDef2.toJson(mapper)))
//        println(markDef2.toJson(mapper))
    }
}