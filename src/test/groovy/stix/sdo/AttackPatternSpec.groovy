package stix.sdo

import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.AttackPattern
import spock.lang.Specification
import spock.lang.Unroll

class AttackPatternSpec extends Specification implements StixMockDataGenerator {

    @Unroll
    def "Generate Attack Pattern Data: Run: '#i'"() {
        when: "Generating Attack Pattern Data"
            AttackPattern originalAttackPattern = mockAttackPattern()
            println "Original Object: ${originalAttackPattern.toString()}"

        then: "Convert Attack Pattern to Json"
            String originalJson = originalAttackPattern.toJsonString()
            println "Original Json: ${originalJson}"

        then: "Parse Json back into Attack Pattern Object"
            AttackPattern parsedAttackPattern = (AttackPattern)StixParsers.parseObject(originalJson)
            println "Parsed Object: ${parsedAttackPattern}"

        then: "Parsed object should match Original object"
            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Attack Pattern Object back to into Json"
            String newJson = parsedAttackPattern.toJsonString()
            println "New Json: ${newJson}"

        then: "New Json should match Original Json"
            assert newJson == originalJson

        where:
            i << (1..100)
    }
}
