package stix.serialization

import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.domainobjects.AttackPattern
import io.digitalstate.stix.domainobjects.types.KillChainPhase
import io.digitalstate.stix.helpers.StixDataFormats
import spock.lang.Specification

import java.time.Instant
import java.time.ZoneId


class AttackPatternSpec extends Specification {

    def "AttackPattern 1 json"(){

        when: "load AttackPattern1.json"
        URL jsonUrl = getClass().getResource('/stix/json/domainobjects/AttackPattern1.json')

        ObjectMapper objectMapper = StixDataFormats.getJsonMapper()

        AttackPattern attackPattern = objectMapper.readValue(jsonUrl, AttackPattern.class)

        then:
        assert attackPattern.getType() == 'attack-pattern'
        assert attackPattern.getId() == 'attack-pattern--477b763e-226f-46b3-a211-3cb5b86978a6'
        assert attackPattern.getCreated() == Instant.parse('2018-11-26T01:17:26.416Z').atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.getModified() == Instant.parse('2018-11-29T01:17:26.416Z').atZone(ZoneId.of("Etc/UTC"))
        assert !attackPattern.revoked
        assert attackPattern.getName() == "some pattern"

    }

    def "AttackPattern 2 json"(){

        when: "load AttackPattern2.json"
        URL jsonUrl = getClass().getResource('/stix/json/domainobjects/AttackPattern2.json')

        ObjectMapper objectMapper = StixDataFormats.getJsonMapper()

        AttackPattern attackPattern = objectMapper.readValue(jsonUrl, AttackPattern.class)

        then:
        assert attackPattern.getType() == 'attack-pattern'
        assert attackPattern.getId() == 'attack-pattern--477b763e-226f-46b3-a211-3cb5b86978a6'
        assert attackPattern.getCreated() == Instant.parse('2018-11-26T01:17:26.416Z').atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.getModified() == Instant.parse('2018-11-29T01:17:26.416Z').atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.revoked == false
        assert attackPattern.getName() == "some pattern"


        assert attackPattern.getKillChainPhases().size() == 2

        LinkedHashSet<KillChainPhase> kcpSet = new LinkedHashSet<>();
        kcpSet.add(new KillChainPhase("Chain1", "phase1"))
        kcpSet.add(new KillChainPhase("Chain1", "phase2"))
        assert attackPattern.getKillChainPhases() == kcpSet

    }

    def "AttackPattern 3 json"(){

        when: "load AttackPattern3.json"
        URL jsonUrl = getClass().getResource('/stix/json/domainobjects/AttackPattern3.json')

        ObjectMapper objectMapper = StixDataFormats.getJsonMapper()

        AttackPattern attackPattern = objectMapper.readValue(jsonUrl, AttackPattern.class)

        then:
        assert attackPattern.getType() == 'attack-pattern'
        assert attackPattern.getId() == 'attack-pattern--477b763e-226f-46b3-a211-3cb5b86978a6'
        assert attackPattern.getCreated() == Instant.parse('2018-11-26T01:17:26.416Z').atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.getModified() == Instant.parse('2018-11-29T01:17:26.416Z').atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.revoked == false
        assert attackPattern.getName() == "some pattern"


        assert attackPattern.getKillChainPhases().size() == 2

        LinkedHashSet<KillChainPhase> kcpSet = new LinkedHashSet<>()
        kcpSet.add(new KillChainPhase('Chain1', 'phase1'))
        kcpSet.add(new KillChainPhase('Chain1', 'phase2'))
        assert attackPattern.getKillChainPhases() == kcpSet

        assert attackPattern.getObjectMarkingRefs().size() == 1
        assert attackPattern.getObjectMarkingRefsIds()[0] == 'marking-definition--b17db0c7-1c2e-4c6a-9cab-34d0cacfbf50'
        assert attackPattern.getObjectMarkingRefs()[0].hasObject() == false
        assert attackPattern.getObjectMarkingRefs()[0].getId() == 'marking-definition--b17db0c7-1c2e-4c6a-9cab-34d0cacfbf50'

    }

    def "AttackPattern 4 json"(){

        when: "load AttackPattern4.json"
        URL jsonUrl = getClass().getResource('/stix/json/domainobjects/AttackPattern4.json')

        ObjectMapper objectMapper = StixDataFormats.getJsonMapper()

        AttackPattern attackPattern = objectMapper.readValue(jsonUrl, AttackPattern.class)

        then: "Basic Properties are accurate"
        assert attackPattern.getType() == 'attack-pattern'
        assert attackPattern.getId() == 'attack-pattern--477b763e-226f-46b3-a211-3cb5b86978a6'
        assert attackPattern.getCreated() == Instant.parse('2018-11-26T01:17:26.416Z').atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.getModified() == Instant.parse('2018-11-29T01:17:26.416Z').atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.revoked == false
        assert attackPattern.getName() == "some pattern"

        then: "KillChainPhases Array is accurate"
        assert attackPattern.getKillChainPhases().size() == 2

        LinkedHashSet<KillChainPhase> kcpSet = new LinkedHashSet<>()
        kcpSet.add(new KillChainPhase('Chain1', 'phase1'))
        kcpSet.add(new KillChainPhase('Chain1', 'phase2'))
        assert attackPattern.getKillChainPhases() == kcpSet

        then: "ObjectMarkingRefs is accurate"
        assert attackPattern.getObjectMarkingRefs().size() == 1
        assert attackPattern.getObjectMarkingRefsIds()[0] == 'marking-definition--b17db0c7-1c2e-4c6a-9cab-34d0cacfbf50'
        assert attackPattern.getObjectMarkingRefs()[0].hasObject() == false
        assert attackPattern.getObjectMarkingRefs()[0].getId() == 'marking-definition--b17db0c7-1c2e-4c6a-9cab-34d0cacfbf50'

        then: "GranularMarkings size and GranularMarkings' selector size is accurate"
        assert attackPattern.getGranularMarkings().size() == 1
        assert attackPattern.getGranularMarkings()[0].getSelectors().size() == 3

        then: "GranularMarking selectors have proper values"
        LinkedHashSet<String> selectors = new LinkedHashSet<>(Arrays.asList("pattern1", "pattern2", "pattern3"))
        assert attackPattern.getGranularMarkings()[0].getSelectors() == selectors

        then: "GranularMarking MarkingRef was accurately loaded"
        assert attackPattern.getGranularMarkings()[0].getMarkingRefId() == 'marking-definition--0f1a0afd-ba25-47a2-b7e1-4d0ab65b1689'
        assert attackPattern.getGranularMarkings()[0].getMarkingRef().hasObject() == false
        assert attackPattern.getGranularMarkings()[0].getMarkingRef().getId() == 'marking-definition--0f1a0afd-ba25-47a2-b7e1-4d0ab65b1689'

    }
}