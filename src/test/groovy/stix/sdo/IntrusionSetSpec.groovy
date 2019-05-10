package stix.sdo

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.IntrusionSet
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import faker.StixMockDataGenerator

class IntrusionSetSpec extends Specification {

    @Shared ObjectMapper mapper = new ObjectMapper()
    @Shared StixMockDataGenerator stixMockDataGenerator = new StixMockDataGenerator()

    @Unroll
    def "Generate Intrusion Set Data: Run: '#i'"() {
        when: "Generating Intrusion Set Data"
            IntrusionSet originalIntrusionSet = stixMockDataGenerator.mockIntrusionSet()
//            println "Original Object: ${originalIntrusionSet.toString()}"

        then: "Convert Intrusion Set to Json"
            JsonNode originalJson = mapper.readTree(originalIntrusionSet.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
//            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Intrusion Set Object"
            IntrusionSet parsedIntrusionSet = (IntrusionSet)StixParsers.parseObject(originalJsonString)
            IntrusionSet parsedIntrusionSetGeneric = StixParsers.parse(originalJsonString, IntrusionSet.class)
//            println "Parsed Object: ${parsedIntrusionSet}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Intrusion Set back to into Json"
            JsonNode newJson =  mapper.readTree(parsedIntrusionSet.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
//            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
            i << (1..100)
    }
}
