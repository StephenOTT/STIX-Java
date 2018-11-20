package io.digitalstate.charon.camunda;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.digitalstate.stix.bundle.Bundle;

import io.digitalstate.stix.cyberobservableobjects.Artifact;
import io.digitalstate.stix.cyberobservableobjects.AutonomousSystem;

import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
import io.digitalstate.stix.datamarkings.markingtypes.StatementMarking;
import io.digitalstate.stix.datamarkings.markingtypes.TlpMarking;
import io.digitalstate.stix.domainobjects.AttackPattern;
import io.digitalstate.stix.domainobjects.ObservedData;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;

@SpringBootApplication
public class CharonApplication {
    public static void main(String... args) throws JsonProcessingException {
        SpringApplication.run(CharonApplication.class, args);

        AttackPattern attackPattern = new AttackPattern("some pattern");
        attackPattern.setKillChainPhases(
                new KillChainPhase("Chain1", "phase1"),
                new KillChainPhase("Chain1", "phase2"));

        attackPattern.setModified(attackPattern.getCreated().plusDays(3));

        MarkingDefinition markingDefinition = new MarkingDefinition(
                new TlpMarking("white"));

        attackPattern.addObjectMarkingRefs(markingDefinition);

        MarkingDefinition statement1 = new MarkingDefinition(
                new StatementMarking("Internal review of data allows for sharing as per ABC-009 Standard"));

        markingDefinition.addObjectMarkingRefs(statement1);

        ZonedDateTime observedTime = ZonedDateTime.now();
        ObservedData observedData = new ObservedData(observedTime, observedTime, 3,
                new Artifact(){{
                    setUrl("someURL");
                }},
                new AutonomousSystem(3){{
                    setRir("someRIR");
                }});
        observedData.addObjectMarkingRefs(statement1);

        Bundle bundle = new Bundle(attackPattern);

        bundle.addObjects(observedData);

        bundle.autoAddDataMarkingsToBundle();

        System.out.println(attackPattern.toJsonString());
        System.out.println(bundle.toJsonString());
    }
}

