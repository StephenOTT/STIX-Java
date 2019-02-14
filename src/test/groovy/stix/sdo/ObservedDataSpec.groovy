package stix.sdo

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.ObservedData
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ObservedDataSpec extends Specification implements StixMockDataGenerator {

    @Shared ObjectMapper mapper = new ObjectMapper()

    @Unroll
    def "Generate Observed-Data Data: Run: '#i'"() {
        when: "Generating Observed-Data Data"
            ObservedData originalObservedData = mockObservedData()
            println "Original Object: ${originalObservedData.toString()}"

        then: "Convert Observed-Data to Json"
            JsonNode originalJson = mapper.readTree(originalObservedData.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Observed-Data Object"
            ObservedData parsedObservedData = (ObservedData)StixParsers.parseObject(originalJsonString)
            println "Parsed Object: ${parsedObservedData}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Observed-Data back to into Json"
            JsonNode newJson =  mapper.readTree(parsedObservedData.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
            i << (1..1000) // More tests are run because of the large variation of probabilities and number of combinations
    }
}
