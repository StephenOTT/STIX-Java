package stix.bundle

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.bundle.BundleObject
import io.digitalstate.stix.json.StixParsers
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import stix.sdo.StixMockDataGenerator

class BundleSpec extends Specification implements StixMockDataGenerator {

    @Shared ObjectMapper mapper = new ObjectMapper()

    @Unroll
    def "Generate Bundle Data: Run: '#i'"() {
        when: "Generating Bundle Data"
        Bundle originalBundle = mockBundle()
            println "Original Object: ${originalBundle.toString()}"

        then: "Convert Bundle to Json"
            JsonNode originalJson = mapper.readTree(originalBundle.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Bundle Object"
            BundleObject parsedBundle = StixParsers.parseBundle(originalJsonString)
            println "Parsed Object: ${parsedBundle}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Bundle back to into Json"
            JsonNode newJson =  mapper.readTree(parsedBundle.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
            i << (1..100) // More tests are run because of the large variation of probabilities and number of combinations
    }
}
