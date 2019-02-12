package stix.sdo

import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.Indicator
import io.digitalstate.stix.sdo.objects.IntrusionSet
import spock.lang.Specification
import spock.lang.Unroll

class IntrusionSetSpec extends Specification implements StixMockDataGenerator {

    @Unroll
    def "Generate Intrusion Set Data: Run: '#i'"() {
        when: "Generating Intrusion Set Data"
        IntrusionSet originalIntrusionSet = mockIntrusionSet()
            println "Original Object: ${originalIntrusionSet.toString()}"

        then: "Convert Intrusion Set to Json"
            String originalJson = originalIntrusionSet.toJsonString()
            println "Original Json: ${originalJson}"

        then: "Parse Json back into Intrusion Set Object"
            IntrusionSet parsedIndicator = (IntrusionSet)StixParsers.parseObject(originalJson)
            println "Parsed Object: ${parsedIndicator}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Intrusion Set back to into Json"
            String newJson = parsedIndicator.toJsonString()
            println "New Json: ${newJson}"

        then: "New Json should match Original Json"
            assert newJson == originalJson

        where:
            i << (1..100)
    }
}
