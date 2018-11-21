package io.digitalstate.charon.camunda;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.digitalstate.stix.bundle.Bundle;

import io.digitalstate.stix.cyberobservableobjects.Artifact;
import io.digitalstate.stix.cyberobservableobjects.AutonomousSystem;

import io.digitalstate.stix.cyberobservableobjects.CyberObservableObject;
import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
import io.digitalstate.stix.datamarkings.granular.GranularMarking;
import io.digitalstate.stix.datamarkings.markingtypes.StatementMarking;
import io.digitalstate.stix.datamarkings.markingtypes.TlpMarking;
import io.digitalstate.stix.domainobjects.AttackPattern;
import io.digitalstate.stix.domainobjects.ObservedData;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;
import java.util.HashMap;

@SpringBootApplication
public class CharonApplication {
    public static void main(String... args) throws JsonProcessingException {
        SpringApplication.run(CharonApplication.class, args);

        // Generate Attack Pattern:

        AttackPattern attackPattern = new AttackPattern("some pattern");
        attackPattern.setKillChainPhases(
                new KillChainPhase("Chain1", "phase1"),
                new KillChainPhase("Chain1", "phase2"));

        attackPattern.setModified(attackPattern.getCreated().plusDays(3));

        HashMap<String, Object> customProperties = new HashMap<>();
        customProperties.put("someCustomKey", "My custom value");
        customProperties.put("someOtherCustom_key", 3939);
        attackPattern.setCustomProperties(customProperties);

        MarkingDefinition markingDefinition = new MarkingDefinition(
                new TlpMarking("white"));

        MarkingDefinition refDef = new MarkingDefinition(
                new TlpMarking("red"));

        attackPattern.addObjectMarkingRefs(markingDefinition);
        GranularMarking granularMarking =
                new GranularMarking(refDef, "pattern1", "pattern2", "pattern3");

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


        // Generate Bundle:

        Bundle bundle = new Bundle(attackPattern);

        bundle.addObjects(observedData);

        bundle.autoAddDataMarkingsToBundle();

        System.out.println(attackPattern.toJsonString());
        System.out.println(bundle.toJsonString());
    }
}

