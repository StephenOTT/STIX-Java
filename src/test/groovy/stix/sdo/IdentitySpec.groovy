package stix.sdo

import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.Identity
import spock.lang.Specification
import spock.lang.Unroll

class IdentitySpec extends Specification implements StixMockDataGenerator {

    @Unroll
    def "Generate Identity Data: Run: '#i'"() {
        when: "Generating Identity Data"
        Identity originalIdentity = mockIdentity()
            println "Original Object: ${originalIdentity.toString()}"

        then: "Convert Identity to Json"
            String originalJson = originalIdentity.toJsonString()
            println "Original Json: ${originalJson}"

        then: "Parse Json back into Attack Pattern Object"
            Identity parsedIdentity = (Identity)StixParsers.parseObject(originalJson)
            println "Parsed Object: ${parsedIdentity}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Identity Object back to into Json"
            String newJson = parsedIdentity.toJsonString()
            println "New Json: ${newJson}"

        then: "New Json should match Original Json"
            assert newJson == originalJson

        where:
            i << (1..100)
    }
}
