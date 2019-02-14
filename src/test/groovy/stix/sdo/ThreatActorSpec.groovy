package stix.sdo

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.ThreatActor
import spock.lang.Specification
import spock.lang.Unroll

class ThreatActorSpec extends Specification implements StixMockDataGenerator {

    @Unroll
    def "Generate Threat Actor Data: Run: '#i'"() {
        when: "Generating Threat Actor Data"
        ThreatActor originalThreatActor = mockThreatActor()
            println "Original Object: ${originalThreatActor.toString()}"

        then: "Convert Threat Actor to Json"
            def originalJson = new JsonSlurper().parseText(originalThreatActor.toJsonString())
            String originalJsonString = new JsonBuilder(originalJson).toString()
            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Threat Actor Object"
            ThreatActor parsedThreatActor = (ThreatActor)StixParsers.parseObject(originalJsonString)
            println "Parsed Object: ${parsedThreatActor}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Threat Actor back to into Json"
            def newJson =  new JsonSlurper().parseText(parsedThreatActor.toJsonString())
            String newJsonString = new JsonBuilder(newJson).toString()
            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            assert newJson == originalJson

        where:
            i << (1..100) // More tests are run because of the large variation of probabilities and number of combinations
    }
}
