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

        then: "Convert attack Pattern to JSON"
            String originalJson = originalAttackPattern.toJsonString()
            println originalJson

        then: "Parse Json back into Attack Pattern Object"
            AttackPattern parsedAttackPattern = (AttackPattern)StixParsers.parseObject(originalJson)

        then: "Parsed object should match Original object"
            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Attack Pattern back to into JSON"
            String newJson = parsedAttackPattern.toJsonString()
            println newJson

        then: "New Json should match Original Json"
            assert newJson == originalJson

        where:
            i << (1..100)
    }
}
