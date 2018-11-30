package stix.serialization

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.bundle.BundleObject
import io.digitalstate.stix.domainobjects.AttackPattern
import io.digitalstate.stix.domainobjects.types.KillChainPhase
import spock.lang.Shared
import spock.lang.Specification

import java.time.Instant
import java.time.ZoneId


class AttackPatternSpec extends Specification {

    @Shared String jsonPath = '/stix/json/domainobjects/AttackPattern-Full-Bundle-1.json'
    @Shared ObjectMapper mapper = new ObjectMapper()
    @Shared URL jsonUrl = getClass().getResource(jsonPath)
    @Shared JsonNode fullJson = mapper.readTree(jsonUrl)

    def "AttackPattern Minimum Properties"() {
        setup: "Set json to only have minimum required fields"
        JsonNode json = fullJson.deepCopy()
        ObjectNode sdoJson = (ObjectNode)json.get("objects").get(0)
        sdoJson = (ObjectNode)sdoJson.retain(["type",
                                              "id",
                                              "created",
                                              "modified",
                                              "revoked",
                                              "name"])

        when: "Parsing json into Attack Property"
        AttackPattern attackPattern = AttackPattern.parse(mapper.writeValueAsString(sdoJson))

        then: "Basic Properties are accurate"
        assert attackPattern.getType() == sdoJson.get("type").asText()
        assert attackPattern.getId() == sdoJson.get("id").asText()
        assert attackPattern.getCreated() == Instant.parse(sdoJson.get("created").asText()).atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.getModified() == Instant.parse(sdoJson.get("modified").asText()).atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.revoked == sdoJson.get("revoked").asBoolean()
        assert attackPattern.getName() == sdoJson.get("name").asText()
    }

    def "AttackPattern with all possible properties"() {
        setup: "Set json to only have minimum required fields"
        JsonNode json = fullJson.deepCopy()
        ObjectNode sdoJson = (ObjectNode)json.get("objects").get(0)

        when: "Parsing json into Attack Property"
        AttackPattern attackPattern = AttackPattern.parse(mapper.writeValueAsString(sdoJson))

        then: "Basic Properties are accurate"
        assert attackPattern.getType() == sdoJson.get("type").asText()
        assert attackPattern.getId() == sdoJson.get("id").asText()
        assert attackPattern.getCreated() == Instant.parse(sdoJson.get("created").asText()).atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.getModified() == Instant.parse(sdoJson.get("modified").asText()).atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.revoked == sdoJson.get("revoked").asBoolean()
        assert attackPattern.getName() == sdoJson.get("name").asText()

        then: "description is accurate"
        assert attackPattern.getDescription() == sdoJson.get("description").asText()

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

    def "AttackPattern Minimum Properties without revoked field"() {
        setup: "Set json to only have minimum required fields"
        JsonNode json = fullJson.deepCopy()
        ObjectNode sdoJson = (ObjectNode)json.get("objects").get(0)
        sdoJson = (ObjectNode)sdoJson.retain(["type",
                                              "id",
                                              "created",
                                              "modified",
                                              "name"])

        when: "Parsing json into Attack Property"
        AttackPattern attackPattern = AttackPattern.parse(mapper.writeValueAsString(sdoJson))

        then: "Basic Properties are accurate"
        assert attackPattern.getType() == sdoJson.get("type").asText()
        assert attackPattern.getId() == sdoJson.get("id").asText()
        assert attackPattern.getCreated() == Instant.parse(sdoJson.get("created").asText()).atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.getModified() == Instant.parse(sdoJson.get("modified").asText()).atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.revoked == sdoJson.get("revoked").asBoolean()
        assert attackPattern.getName() == sdoJson.get("name").asText()
    }

    def "Bundle with AttackPattern with all possible properties"() {
        setup: "Set json to only have minimum required fields"
        JsonNode json = fullJson.deepCopy()
//        ObjectNode sdoJson = (ObjectNode)json.get("objects").get(0)

        when: "Parsing json into Attack Property"
        Bundle bundle = Bundle.parse(mapper.writeValueAsString(json))

        then: "Bundle has accurate properties"
        assert bundle.getType() == json.get("type").asText()
        assert bundle.getId() == json.get("id").asText()
        assert bundle.getSpecVersion() == json.get("spec_version").asText()

        assert bundle.getObjects().size() == 1

        assert bundle.getObjects()[0] instanceof AttackPattern

        ObjectNode sdoJson = (ObjectNode)json.get("objects").get(0)
        AttackPattern attackPattern = (AttackPattern)bundle.getObjects()[0]

        then: "Basic Properties are accurate"
        assert attackPattern.getType() == sdoJson.get("type").asText()
        assert attackPattern.getId() == sdoJson.get("id").asText()
        assert attackPattern.getCreated() == Instant.parse(sdoJson.get("created").asText()).atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.getModified() == Instant.parse(sdoJson.get("modified").asText()).atZone(ZoneId.of("Etc/UTC"))
        assert attackPattern.revoked == sdoJson.get("revoked").asBoolean()
        assert attackPattern.getName() == sdoJson.get("name").asText()

        then: "description is accurate"
        assert attackPattern.getDescription() == sdoJson.get("description").asText()

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