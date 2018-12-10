package stix.desrialization

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException
import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.helpers.StixDataFormats
import io.digitalstate.stix.sdo.objects.AttackPattern
import io.digitalstate.stix.sdo.objects.Malware
import io.digitalstate.stix.sro.objects.Relationship
import spock.lang.Specification

class BundleSpec extends Specification {

    def "Basic Derived-From Relationship in Bundle"(){
        when:
        Relationship usesRel = Relationship.builder()
                .relationshipType("uses")
                .sourceRef(AttackPattern.builder()
                        .name("Some Attack Pattern 1")
                        .build())
                .targetRef(Malware.builder()
                        .name("dog")
                        .addLabels("worm")
                        .build())
                .build()
////
//        AttackPattern customAttackPattern = AttackPattern.builder()
//                .name("My Custom Pattern")
//                .build()

//        Malware malware = Malware.builder()
//                .name("Some Malware")
//                .addLabels("worm")
//                .build()
//
//        Malware malwareModified = malware.withLabels("adware")

        then:
        ObjectMapper mapper = StixDataFormats.getJsonMapper(true);

        String usesRelString = mapper.writeValueAsString(usesRel)
        println usesRelString

        Relationship parsedRelationship = mapper.readValue(usesRelString, Relationship.class)
        println parsedRelationship

//        String malwareString = mapper.writeValueAsString(malware)
//        println malwareString
//
//        Malware malwareParsed = mapper.readValue(malwareString, Malware.class)
//        println malwareParsed

//        String attackString = mapper.writeValueAsString(customAttackPattern)
//
//        AttackPattern attackPattern = mapper.readValue(attackString, AttackPattern.class)
//        String attackPatternString = mapper.writeValueAsString(attackPattern)
//        println "Attack Pattern Json"
//        println attackPatternString

//        Bundle bundle = Bundle.builder()
//                .addObjects(attackPattern, derived, malwareModified)
//                .build()
//
//        String bundleString = mapper.writeValueAsString(bundle)
//        println "Bundle Json:"
//        println bundleString
//
//        Bundle bundleParsed = mapper.readValue(bundleString, Bundle.class)
//        println "Bundle Json Parsed into Object:"
//        println bundleParsed

    }
}