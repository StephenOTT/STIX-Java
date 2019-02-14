package stix.sdo

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.AttackPattern
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class AttackPatternSpec extends Specification implements StixMockDataGenerator {

    @Shared ObjectMapper mapper = new ObjectMapper()

    @Unroll
    def "Generate Attack Pattern Data: Run: '#i'"() {
        when: "Generating Attack Pattern Data"
            AttackPattern originalAttackPattern = mockAttackPattern()
            println "Original Object: ${originalAttackPattern.toString()}"

        then: "Convert Attack Pattern to Json"
            JsonNode originalJson = mapper.readTree(originalAttackPattern.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Attack Pattern Object"
            AttackPattern parsedAttackPattern = (AttackPattern)StixParsers.parseObject(originalJsonString)
            println "Parsed Object: ${parsedAttackPattern}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Attack Pattern Object back to into Json"
            JsonNode newJson =  mapper.readTree(parsedAttackPattern.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
            i << (1..100)
    }
}
