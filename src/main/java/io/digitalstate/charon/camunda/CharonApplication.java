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

        ObjectMapper om = StixDataFormats.getJsonMapper();
        AttackPattern atkPostString = null;
        try {
            atkPostString = om.readValue(attackPatternString1, AttackPattern.class );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON->Object->JSON:");
        System.out.println(atkPostString.toJsonString());


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

