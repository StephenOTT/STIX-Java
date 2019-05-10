package stix.datamarkings

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.datamarkings.MarkingDefinition
import io.digitalstate.stix.json.StixParsers
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import faker.StixMockDataGenerator

class MarkingDefinitionSpec extends Specification{

    @Shared ObjectMapper mapper = new ObjectMapper()
    @Shared StixMockDataGenerator stixMockDataGenerator = new StixMockDataGenerator()

    @Unroll
    def "Generate Marking Definition Data: Run: '#i'"() {
        when: "Generating Marking Definition Data"
            MarkingDefinition originalMarkingDefinition = stixMockDataGenerator.mockMarkingDefinition()
//            println "Original Object: ${originalMarkingDefinition.toString()}"

        then: "Convert Marking Definition to Json"
            JsonNode originalJson = mapper.readTree(originalMarkingDefinition.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
//            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Marking Definition Object"
            MarkingDefinition parsedMarkingDefinition = (MarkingDefinition)StixParsers.parseObject(originalJsonString)
            MarkingDefinition parsedMarkingDefinitionGeneric = StixParsers.parse(originalJsonString, MarkingDefinition.class)
//            println "Parsed Object: ${parsedMarkingDefinition}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalMarkingDefinition == parsedMarkingDefinition

        then: "Convert Parsed Marking Definition Object back to into Json"
            JsonNode newJson =  mapper.readTree(parsedMarkingDefinition.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
//            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
            i << (1..100)
    }
}
