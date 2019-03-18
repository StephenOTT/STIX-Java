package stix.validation

import io.digitalstate.stix.json.StixParserValidationException
import io.digitalstate.stix.json.StixParsers
import spock.lang.Specification

class AttackPatternValidationSpec extends Specification {

    def "Validate invalid Attack-Pattern JSON"() {
        when: "Parsing invalid Attack-Pattern JSON "
        String json = '''
            {
              "type": "attack-pattern",
              "id": "attack-pattern--17e6110c-0f51-4d91-8c1c-417d3f886bda",
              "created_by_ref": "identity--826cf0f0-2105-4cf3-a56a-06998d17b1ec",
              "created": "2019-03-13T21:41:01.373Z",
              "modified": "2019-03-13T21:41:01.374Z",
              "revoked": true,
              "labels": [
                "111",
                "crabbing",
                "metricized",
                "potentates",
                "cresylic",
                "ultrasonic"
              ],
              "object_marking_refs": [
                "marking-definition--4805e1e6-f9c8-476c-bae3-1fa8b3a89197",
                "marking-definition--3966fea2-f4a3-4eac-8cb8-c37be13e7fe5",
                "marking-definition--2301e050-08c1-4d68-abe1-372a9c9bf0af"
              ],
              "granular_markings": [
                {
                  "marking_ref": "marking-definition--a4cb9815-d20e-41d7-a729-e2f3d432144f",
                  "selectors": [
                    "tithe"
                  ]
                },
                {
                  "marking_ref": "marking-definition--d15d48de-abc7-4c3e-a3ba-bad2198478c4",
                  "selectors": [
                    "cozily",
                    "atremble",
                    "twaddle",
                    "moreish",
                    "cruciferous",
                    "recommence",
                    "bluntly",
                    "scudded",
                    "quiescent"
                  ]
                },
                {
                  "marking_ref": "marking-definition--1a6676db-d3f9-403f-8975-3ba1f8f983a6",
                  "selectors": [
                    "adagio",
                    "teff",
                    "eburnation",
                    "sousaphones",
                    "whisks"
                  ]
                },
                {
                  "marking_ref": "marking-definition--ad10add9-9008-45c7-b40b-0ccb27bb9c27",
                  "selectors": [
                    "neuk",
                    "reuses",
                    "squat"
                  ]
                }
              ],
              "name": "dog",
              "description": "quicksilver nobbut lame subahs heathfowl slightly bountifulness vitellines creepies custom both boldly darkly unwooded",
              "x_breakfasts": 848823,
              "xx_gats": "respondent persistently trephining anodizes washiest untimely jibe"
            }
                    '''

        then: "Should have 1 error"
        try {
            StixParsers.parseObject(json)
        } catch (StixParserValidationException ex) {
            assert ex.getConstraintValidations().size() == 1
//            ex.getConstraintValidations().each { x ->
//                println "------"
//                println "Type: ${x.getRootBean().getClass().getSimpleName()}"
//                println "Message: ${x.getMessage()}"
//                println "path: ${x.getPropertyPath()}"
//                println "invalid_value: ${x.getInvalidValue().toString()}"
//                println "------"
//            }
        }
    }

}
