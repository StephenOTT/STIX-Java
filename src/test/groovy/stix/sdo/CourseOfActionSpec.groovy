package stix.sdo

import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.CourseOfAction
import io.digitalstate.stix.sdo.objects.Identity
import spock.lang.Specification
import spock.lang.Unroll

class CourseOfActionSpec extends Specification implements StixMockDataGenerator {

    @Unroll
    def "Generate Course of Action Data: Run: '#i'"() {
        when: "Generating Identity Data"
        CourseOfAction originalCourseOfAction = mockCourseOfAction()
            println "Original Object: ${originalCourseOfAction.toString()}"

        then: "Convert Course of Action to Json"
            String originalJson = originalCourseOfAction.toJsonString()
            println "Original Json: ${originalJson}"

        then: "Parse Json back into Course of Action Object"
            CourseOfAction parsedCourseOfAction = (CourseOfAction)StixParsers.parseObject(originalJson)
            println "Parsed Object: ${parsedCourseOfAction}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Identity Object back to into Json"
            String newJson = parsedCourseOfAction.toJsonString()
            println "New Json: ${newJson}"

        then: "New Json should match Original Json"
            assert newJson == originalJson

        where:
            i << (1..100)
    }
}
