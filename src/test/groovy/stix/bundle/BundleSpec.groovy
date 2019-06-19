package stix.bundle

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.bundle.BundleableObject
import io.digitalstate.stix.json.StixParsers
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import faker.StixMockDataGenerator

class BundleSpec extends Specification {

    @Shared
    ObjectMapper mapper = new ObjectMapper()
    @Shared
    StixMockDataGenerator stixMockDataGenerator = new StixMockDataGenerator()

    @Unroll
    def "Generate Bundle Data: Run: '#i'"() {
        when: "Generating Bundle Data"
        Bundle originalBundle = stixMockDataGenerator.mockBundle(1, 2)

        JsonNode originalJson = mapper.readTree(originalBundle.toJsonString())
        String originalJsonString = mapper.writeValueAsString(originalJson)
//            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Bundle Object"
        Bundle parsedBundle = (Bundle) StixParsers.parseBundle(originalJsonString)
        Bundle parsedBundleGeneric = StixParsers.parse(originalJsonString, Bundle.class)
//            println "Parsed Object: ${parsedBundle}"

        //@TODO needs to be setup to handle dehydrated object comparison
        then: "Parsed object should match Original object"
//        assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Bundle back to into Json"
        JsonNode newJson = mapper.readTree(parsedBundle.toJsonString())
        String newJsonString = mapper.writeValueAsString(newJson)
//            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
        JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
        i << (1..100) // More tests are run because of the large variation of probabilities and number of combinations
    }

    def "Parse Indicator Bundle"() {

        when: "setup file access to bundle"

        String bundleJson = getClass()
                .getResource("/stix/baseline/json/sdo/indicator/indicators.json").getText("UTF-8")

        then: "Parse json into bundle"
        Bundle bundle = (Bundle) StixParsers.parseBundle(bundleJson)
//        println bundle.toJsonString()

        and: "the original bundle json matches the parsed object that was converted back to json"
        assert mapper.readTree(bundle.toJsonString()) == mapper.readTree(bundleJson)
    }

    @Ignore
    def "Generate File of a large Bundle"() {
        when: "Generating Bundle Data"
        Bundle originalBundle = stixMockDataGenerator.mockBundle(1, 2)

        then: "Convert Bundle to Json"
        FileWriter fileWriter = new FileWriter(new File("./bundle-large.json"))

        JsonGenerator jg = StixParsers.getJsonMapper()
                .getFactory()
                .createGenerator(fileWriter)

        // Change this value to generate different sizes
        int objectCount = 50000

        jg.writeStartObject()

        jg.writeStringField("id", originalBundle.getId())
        jg.writeStringField("type", originalBundle.getType())
        jg.writeStringField("spec_version", originalBundle.getSpecVersion())

        jg.writeArrayFieldStart("objects")
        objectCount.times({ o ->
            jg.writeObject(stixMockDataGenerator.generateRandomBundleableObject())
        })
        jg.writeEndArray()

        jg.writeEndObject()

        jg.close()
        fileWriter.close()
    }

    @Ignore
    def "Parse Large Bundle"() {
        when:
        File file = new File("./bundle-large.json")

        then:
        JsonParser parser = StixParsers.getJsonMapper().getFactory().createParser(file)

        then:
        TreeNode tree = parser.readValueAsTree()
        TreeNode objectsArray = tree.get("objects")
        if (objectsArray.isArray()){
            ((ArrayNode)objectsArray).each {o ->
                BundleableObject bundleableObject = o.traverse(parser.getCodec())
                        .readValueAs(BundleableObject.class)

                println bundleableObject
            }
        }
    }
}
