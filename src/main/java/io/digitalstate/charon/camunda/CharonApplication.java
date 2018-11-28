package io.digitalstate.charon.camunda;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.digitalstate.stix.bundle.Bundle;

import io.digitalstate.stix.cyberobservableobjects.Artifact;
import io.digitalstate.stix.cyberobservableobjects.AutonomousSystem;

import io.digitalstate.stix.cyberobservableobjects.CyberObservableObject;
import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
import io.digitalstate.stix.datamarkings.granular.GranularMarking;
import io.digitalstate.stix.datamarkings.markingtypes.StatementMarking;
import io.digitalstate.stix.datamarkings.markingtypes.TlpMarking;
import io.digitalstate.stix.domainobjects.*;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;
import io.digitalstate.stix.helpers.ObjectSigning;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.relationshipobjects.Sighting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

@SpringBootApplication
public class CharonApplication {
    public static void main(String... args) throws JsonProcessingException {
        SpringApplication.run(CharonApplication.class, args);

        // Covert from String into a Object:

        String fullBundleString = "{\n" +
                "  \"type\": \"bundle\",\n" +
                "  \"id\": \"bundle--7a0fe49d-4569-4e9e-87e1-a81231fd72fb\",\n" +
                "  \"spec_version\": \"2.0\",\n" +
                "  \"objects\": [\n" +
                "    {\n" +
                "      \"type\": \"attack-pattern\",\n" +
                "      \"id\": \"attack-pattern--477b763e-226f-46b3-a211-3cb5b86978a6\",\n" +
                "      \"created\": \"2018-11-26T01:17:26.416Z\",\n" +
                "      \"modified\": \"2018-11-29T01:17:26.416Z\",\n" +
                "      \"revoked\": false,\n" +
                "      \"object_marking_refs\": [\n" +
                "        \"marking-definition--b17db0c7-1c2e-4c6a-9cab-34d0cacfbf50\"\n" +
                "      ],\n" +
                "      \"granular_markings\": [\n" +
                "        {\n" +
                "          \"selectors\": [\n" +
                "            \"pattern1\",\n" +
                "            \"pattern2\",\n" +
                "            \"pattern3\"\n" +
                "          ],\n" +
                "          \"marking_ref\": \"marking-definition--0f1a0afd-ba25-47a2-b7e1-4d0ab65b1689\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"name\": \"some pattern\",\n" +
                "      \"kill_chain_phases\": [\n" +
                "        {\n" +
                "          \"kill_chain_name\": \"Chain1\",\n" +
                "          \"phase_name\": \"phase1\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"kill_chain_name\": \"Chain1\",\n" +
                "          \"phase_name\": \"phase2\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"x_someCustomKey\": \"My custom value\",\n" +
                "      \"x_someOtherCustom_key\": 3939\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"observed-data\",\n" +
                "      \"id\": \"observed-data--79ec3f75-6fbf-4fe7-b7d0-c540fcf65753\",\n" +
                "      \"created\": \"2018-11-26T01:17:26.461Z\",\n" +
                "      \"modified\": \"2018-11-26T01:17:26.461Z\",\n" +
                "      \"revoked\": false,\n" +
                "      \"object_marking_refs\": [\n" +
                "        \"marking-definition--a2eb976a-aa5c-4999-8c34-0b74a0d46bef\"\n" +
                "      ],\n" +
                "      \"first_observed\": \"2018-11-26T01:17:26.448Z\",\n" +
                "      \"last_observed\": \"2018-11-26T01:17:26.448Z\",\n" +
                "      \"number_observed\": 3,\n" +
                "      \"objects\": {\n" +
                "        \"some artifact\": {\n" +
                "          \"type\": \"artifact\",\n" +
                "          \"url\": \"someURL\"\n" +
                "        },\n" +
                "        \"some AS\": {\n" +
                "          \"type\": \"autonomous-system\",\n" +
                "          \"number\": 5,\n" +
                "          \"rir\": \"someRIR\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"sighting\",\n" +
                "      \"id\": \"sighting--cbae7c1e-57ef-47b4-9e50-22d9169c246d\",\n" +
                "      \"created\": \"2018-11-26T01:17:26.941Z\",\n" +
                "      \"modified\": \"2018-11-26T01:17:26.941Z\",\n" +
                "      \"revoked\": false,\n" +
                "      \"sighting_of_ref\": \"attack-pattern--4c465e41-3c02-4667-8887-c4608e6d3ae9\",\n" +
                "      \"where_sighted_refs\": [\n" +
                "        \"identity--d442813b-7e72-49a6-937a-3e351e219a18\"\n" +
                "      ],\n" +
                "      \"summary\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"marking-definition\",\n" +
                "      \"id\": \"marking-definition--b17db0c7-1c2e-4c6a-9cab-34d0cacfbf50\",\n" +
                "      \"created\": \"2018-11-26T01:17:26.435Z\",\n" +
                "      \"object_marking_refs\": [\n" +
                "        \"marking-definition--a2eb976a-aa5c-4999-8c34-0b74a0d46bef\"\n" +
                "      ],\n" +
                "      \"definition_type\": \"tlp\",\n" +
                "      \"definition\": {\n" +
                "        \"tlp\": \"white\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"marking-definition\",\n" +
                "      \"id\": \"marking-definition--a2eb976a-aa5c-4999-8c34-0b74a0d46bef\",\n" +
                "      \"created\": \"2018-11-26T01:17:26.444Z\",\n" +
                "      \"granular_markings\": [\n" +
                "        {\n" +
                "          \"selectors\": [\n" +
                "            \"marking-pattern1\",\n" +
                "            \"pattern2\",\n" +
                "            \"pattern3\"\n" +
                "          ],\n" +
                "          \"marking_ref\": \"marking-definition--0f1a0afd-ba25-47a2-b7e1-4d0ab65b1689\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"definition_type\": \"statement\",\n" +
                "      \"definition\": {\n" +
                "        \"statement\": \"Internal review of data allows for sharing as per ABC-009 Standard\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"marking-definition\",\n" +
                "      \"id\": \"marking-definition--0f1a0afd-ba25-47a2-b7e1-4d0ab65b1689\",\n" +
                "      \"created\": \"2018-11-26T01:17:26.436Z\",\n" +
                "      \"definition_type\": \"tlp\",\n" +
                "      \"definition\": {\n" +
                "        \"tlp\": \"red\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"relationship\",\n" +
                "      \"id\": \"relationship--40c2a4e3-e5cc-4c8a-98fa-9c411a749beb\",\n" +
                "      \"created\": \"2018-11-26T01:17:26.933Z\",\n" +
                "      \"modified\": \"2018-11-26T01:17:26.933Z\",\n" +
                "      \"revoked\": false,\n" +
                "      \"relationship_type\": \"targets\",\n" +
                "      \"source\": \"attack-pattern--477b763e-226f-46b3-a211-3cb5b86978a6\",\n" +
                "      \"target\": \"identity--d442813b-7e72-49a6-937a-3e351e219a18\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"attack-pattern\",\n" +
                "      \"id\": \"attack-pattern--4c465e41-3c02-4667-8887-c4608e6d3ae9\",\n" +
                "      \"created\": \"2018-11-26T01:17:26.941Z\",\n" +
                "      \"modified\": \"2018-11-26T01:17:26.941Z\",\n" +
                "      \"revoked\": false,\n" +
                "      \"name\": \"someOtherATTK2\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"identity\",\n" +
                "      \"id\": \"identity--d442813b-7e72-49a6-937a-3e351e219a18\",\n" +
                "      \"created\": \"2018-11-26T01:17:26.929Z\",\n" +
                "      \"modified\": \"2018-11-26T01:17:26.929Z\",\n" +
                "      \"revoked\": false,\n" +
                "      \"name\": \"Stephen\",\n" +
                "      \"identity_class\": \"individual\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        String bundleWith1Relation = "{\n" +
                "  \"type\": \"bundle\",\n" +
                "  \"id\": \"bundle--7a0fe49d-4569-4e9e-87e1-a81231fd72fb\",\n" +
                "  \"spec_version\": \"2.0\",\n" +
                "  \"objects\": [\n" +
                "    {\n" +
                "      \"type\": \"relationship\",\n" +
                "      \"id\": \"relationship--40c2a4e3-e5cc-4c8a-98fa-9c411a749beb\",\n" +
                "      \"created\": \"2018-11-26T01:17:26.933Z\",\n" +
                "      \"modified\": \"2018-11-26T01:17:26.933Z\",\n" +
                "      \"revoked\": false,\n" +
                "      \"relationship_type\": \"targets\",\n" +
                "      \"source\": \"attack-pattern--477b763e-226f-46b3-a211-3cb5b86978a6\",\n" +
                "      \"target\": \"identity--d442813b-7e72-49a6-937a-3e351e219a18\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        String attackPatternString1 = "{\n" +
                "  \"type\": \"attack-pattern\",\n" +
                "  \"id\": \"attack-pattern--083048b9-444b-4653-ba26-b9c3d99fccf6\",\n" +
                "  \"created\": \"2018-11-01T01:00:00.000Z\",\n" +
                "  \"modified\": \"2018-11-22T01:00:00.000Z\",\n" +
                "  \"revoked\": false,\n" +
                "  \"name\": \"some pattern\",\n" +
                "  \"kill_chain_phases\": [\n" +
                "    {\n" +
                "      \"kill_chain_name\": \"Chain1\",\n" +
                "      \"phase_name\": \"phase1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"kill_chain_name\": \"Chain1\",\n" +
                "      \"phase_name\": \"phase2\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        String dataMarkingTlp1 = "{\n" +
                "    \"type\": \"marking-definition\",\n" +
                "    \"id\": \"marking-definition--0f1a0afd-ba25-47a2-b7e1-4d0ab65b1689\",\n" +
                "    \"created\": \"2018-11-26T01:17:26.436Z\",\n" +
                "    \"definition_type\": \"tlp\",\n" +
                "    \"definition\": {\n" +
                "      \"tlp\": \"red\"\n" +
                "    }\n" +
                "  }";

        ObjectMapper om = StixDataFormats.getJsonMapper();
        try {
            AttackPattern atkPostString = om.readValue(attackPatternString1, AttackPattern.class);
            System.out.println("AttackPattern::JSON->Object->JSON:");
            System.out.println(atkPostString.toJsonString());

            MarkingDefinition markingDefinitionString = om.readValue(dataMarkingTlp1, MarkingDefinition.class);
            System.out.println("MarkingDefinition::JSON->Object->JSON:");
            System.out.println(markingDefinitionString.toJsonString());

//            Bundle fullBundle = om.readValue(fullBundleString, Bundle.class);
//            fullBundle.hydrateRelationsWithObjects();
//            System.out.println("FullBundle::JSON->Object->JSON:");
////            fullBundle.autoDetectBundleObjects();
//            System.out.println(fullBundle.toJsonString());
//            System.out.println(fullBundle);

            Bundle bundle1Relation = om.readValue(bundleWith1Relation, Bundle.class);
            System.out.println("BundleWith1Relation::JSON->Object->JSON:");
            System.out.println(bundle1Relation.toJsonString());


        } catch (IOException e) {
            e.printStackTrace();
        }


        // Generate Attack Pattern:
        AttackPattern attackPattern = new AttackPattern("some pattern");
        attackPattern.setKillChainPhases(
                new KillChainPhase("Chain1", "phase1"),
                new KillChainPhase("Chain1", "phase2"));

        attackPattern.setModified(attackPattern.getCreated().plusDays(3));

        // Setup Custom properties for Attack Pattern:
        HashMap<String, Object> customProperties = new HashMap<>();
        customProperties.put("someCustomKey", "My custom value");
        customProperties.put("someOtherCustom_key", 3939);
        attackPattern.setCustomProperties(customProperties);

        // Setup Marking Definitions for Attach pattern
        MarkingDefinition markingDefinition = new MarkingDefinition(
                new TlpMarking("white"));

        MarkingDefinition refDef = new MarkingDefinition(
                new TlpMarking("red"));

        // Apply a Object level Marking
        attackPattern.addObjectMarkingRefs(markingDefinition);
        // Create a Granular Marking
        GranularMarking granularMarking =
                new GranularMarking(refDef, "pattern1", "pattern2", "pattern3");

        // Apply a Granular Marking to the attack pattern
        attackPattern.addGranularMarkings(granularMarking);


        MarkingDefinition statement1 = new MarkingDefinition(
                new StatementMarking("Internal review of data allows for sharing as per ABC-009 Standard"));

        GranularMarking markingRestriction =
                new GranularMarking(refDef, "marking-pattern1", "pattern2", "pattern3");

        statement1.addGranularMarkings(markingRestriction);

        markingDefinition.addObjectMarkingRefs(statement1);

        // Generate Observed Data Object:
        ZonedDateTime observedTime = ZonedDateTime.now();
        HashMap<String, CyberObservableObject> cyberObservedObjects = new HashMap<>();
        cyberObservedObjects.put("some artifact",
                new Artifact(){{setUrl("someURL");}}
                );

        cyberObservedObjects.put("some AS",
                new AutonomousSystem(5){{setRir("someRIR");}}
        );

        ObservedData observedData = new ObservedData(observedTime, observedTime, 3, cyberObservedObjects);

        observedData.addObjectMarkingRefs(statement1);

        // Sign Object
        String signedObject = ObjectSigning.signObject(attackPattern);
        customProperties.put("signed_object", signedObject);


        // Generate Bundle.  You must add at least 1 item into the bundle.
        Bundle bundle = new Bundle(attackPattern);

        // Add some additional items into the bundle:
        bundle.addObjects(observedData);


        // Build Identity

        Identity steve = new Identity("Stephen", "individual");
        attackPattern.addTarget(steve);


        // Add a Sighting that is related to attackPattern
        // and has a Object Marking of redDef
        Sighting someSighting = new Sighting(attackPattern);

        AttackPattern ap2 = new AttackPattern("someOtherATTK2");
        someSighting.setWhereSightedRefs(new LinkedHashSet<>(Arrays.asList(steve)));
        bundle.addObjects(someSighting);
        someSighting.setSightingOfRef(ap2);

        IntrusionSet intrusionSet = new IntrusionSet("Some Intrusion");

        // Auto add Data Markings into the Bundle.  This is a helper method that will search all objects in
        // the bundle that can contain Data Markings and add the Data Marking objects as top level items in the Bundle
        // This saves you the effort of having to manually adding the Data markings into the bundle.
        bundle.autoDetectBundleObjects();

        // All objects have had the toJsonString() method added allowing you to print out json string for any Stix related object
        System.out.println(attackPattern.toJsonString());
        System.out.println(bundle.toJsonString());
    }
}

