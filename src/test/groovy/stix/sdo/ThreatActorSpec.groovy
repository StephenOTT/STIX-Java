package stix.sdo

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.ThreatActor
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import stix.StixMockDataGenerator

class ThreatActorSpec extends Specification implements StixMockDataGenerator {

    @Shared ObjectMapper mapper = new ObjectMapper()

    @Unroll
    def "Generate Threat Actor Data: Run: '#i'"() {
        when: "Generating Threat Actor Data"
        ThreatActor originalThreatActor = mockThreatActor()
            println "Original Object: ${originalThreatActor.toString()}"

        then: "Convert Threat Actor to Json"
            JsonNode originalJson = mapper.readTree(originalThreatActor.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Threat Actor Object"
            ThreatActor parsedThreatActor = (ThreatActor)StixParsers.parseObject(originalJsonString)
            println "Parsed Object: ${parsedThreatActor}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Threat Actor back to into Json"
            JsonNode newJson =  mapper.readTree(parsedThreatActor.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
            i << (1..100) // More tests are run because of the large variation of probabilities and number of combinations
    }
}
