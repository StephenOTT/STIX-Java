package stix.custom

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import faker.StixMockDataGenerator
import io.digitalstate.stix.bundle.BundleableObject
import io.digitalstate.stix.custom.StixCustomObject
import io.digitalstate.stix.custom.objects.CustomObject
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.AttackPattern
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class CustomObjectSpec extends Specification {

    @Shared ObjectMapper mapper = new ObjectMapper()

    @Unroll
    def "Generic Object Test 1"() {
        when: "Attempt to Parse a custom object"
            String jsonString = getClass().getResource("/stix/custom/custom_object_1.json").getText("UTF-8")
        StixCustomObject originalObject = (StixCustomObject)StixParsers.parseObject(jsonString)
        CustomObject originalObjectGeneric = StixParsers.parse(jsonString, CustomObject.class)
//            println "Original Object: ${originalAttackPattern.toString()}"

        then:
            println originalObject
//        then: "Convert Attack Pattern to Json"
//            JsonNode originalJson = mapper.readTree(originalAttackPattern.toJsonString())
//            String originalJsonString = mapper.writeValueAsString(originalJson)
////            println "Original Json: ${originalJsonString}"
//
//        then: "Parse Json back into Attack Pattern Object"
//            AttackPattern parsedAttackPattern = (AttackPattern)StixParsers.parseObject(originalJsonString)
////            println "Parsed Object: ${parsedAttackPattern}"
//
//        //@TODO needs to be setup to handle dehydrated object comparison
////        then: "Parsed object should match Original object"
////            assert originalAttackPattern == parsedAttackPattern
//
//        then: "Convert Parsed Attack Pattern Object back to into Json"
//            JsonNode newJson =  mapper.readTree(parsedAttackPattern.toJsonString())
//            String newJsonString = mapper.writeValueAsString(newJson)
////            println "New Json: ${newJsonString}"
//
//        then: "New Json should match Original Json"
//            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)
    }
}
