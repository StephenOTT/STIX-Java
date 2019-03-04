package stix.sro

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sro.objects.Relationship
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import stix.StixMockDataGenerator

class RelationshipSpec extends Specification implements StixMockDataGenerator {

    @Shared ObjectMapper mapper = new ObjectMapper()

    @Unroll
    def "Generate Relationship SRO Data: Run: '#i'"() {
        when: "Generating Relationship SRO Data"
        Relationship originalRelationship = mockRelationship()
//            println "Original Object: ${originalRelationship.toString()}"

        then: "Convert Relationship to Json"
            JsonNode originalJson = mapper.readTree(originalRelationship.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
//            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Relationship Object"
            Relationship parsedRelationship = (Relationship)StixParsers.parseObject(originalJsonString)
//            println "Parsed Object: ${parsedRelationship}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalRelationship == parsedMarkingDefinition

        then: "Convert Parsed Relationship Object back to into Json"
            JsonNode newJson =  mapper.readTree(parsedRelationship.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
//            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
            i << (1..100)
    }
}
