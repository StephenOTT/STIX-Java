package stix.sdo

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.Tool
import spock.lang.Specification
import spock.lang.Unroll

class ToolSpec extends Specification implements StixMockDataGenerator {

    @Unroll
    def "Generate Tool Data: Run: '#i'"() {
        when: "Generating Tool Data"
        Tool originalTool = mockTool()
            println "Original Object: ${originalTool.toString()}"

        then: "Convert Tool to Json"
            def originalJson = new JsonSlurper().parseText(originalTool.toJsonString())
            String originalJsonString = new JsonBuilder(originalJson).toString()
            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Tool Object"
            Tool parsedTool = (Tool)StixParsers.parseObject(originalJsonString)
            println "Parsed Object: ${parsedTool}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Tool back to into Json"
            def newJson =  new JsonSlurper().parseText(parsedTool.toJsonString())
            String newJsonString = new JsonBuilder(newJson).toString()
            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            assert newJson == originalJson

        where:
            i << (1..100) // More tests are run because of the large variation of probabilities and number of combinations
    }
}
