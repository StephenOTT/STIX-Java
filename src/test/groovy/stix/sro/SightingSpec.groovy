package stix.sro

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sro.objects.Sighting
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import faker.StixMockDataGenerator

class SightingSpec extends Specification {

    @Shared ObjectMapper mapper = new ObjectMapper()
    @Shared StixMockDataGenerator stixMockDataGenerator = new StixMockDataGenerator()

    @Unroll
    def "Generate Sighting SRO Data: Run: '#i'"() {
        when: "Generating Sighting SRO Data"
        Sighting originalSighting = stixMockDataGenerator.mockSighting()
//            println "Original Object: ${originalSighting.toString()}"

        then: "Convert Sighting to Json"
            JsonNode originalJson = mapper.readTree(originalSighting.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
//            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Sighting Object"
            Sighting parsedSighting = (Sighting)StixParsers.parseObject(originalJsonString)
//            println "Parsed Object: ${parsedSighting}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalSighting == parsedMarkingDefinition

        then: "Convert Parsed Sighting Object back to into Json"
            JsonNode newJson =  mapper.readTree(parsedSighting.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
//            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
            i << (1..100)
    }
}
