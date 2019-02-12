package stix.sdo

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.ObservedData
import spock.lang.Specification
import spock.lang.Unroll

class ObservedDataSpec extends Specification implements StixMockDataGenerator {

    @Unroll
    def "Generate Observed-Data Data: Run: '#i'"() {
        when: "Generating Observed-Data Data"
            ObservedData originalObservedData = mockObservedData()
            println "Original Object: ${originalObservedData.toString()}"

        then: "Convert Observed-Data to Json"
            def originalJson = new JsonSlurper().parseText(originalObservedData.toJsonString())
            String originalJsonString = new JsonBuilder(originalJson).toString()
            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Observed-Data Object"
            ObservedData parsedObservedData = (ObservedData)StixParsers.parseObject(originalJsonString)
            println "Parsed Object: ${parsedObservedData}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Observed-Data back to into Json"
            def newJson =  new JsonSlurper().parseText(parsedObservedData.toJsonString())
            String newJsonString = new JsonBuilder(newJson).toString()
            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            assert newJson == originalJson

        where:
            i << (1..100)
    }
}
