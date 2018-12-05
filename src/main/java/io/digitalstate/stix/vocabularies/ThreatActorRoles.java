package io.digitalstate.stix.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ThreatActorRoles implements StixVocabulary {

    @JsonProperty("threat_actor_roles_vocabulary")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "agent", "director", "independent",
            "infrastructure-architect", "infrastructure-operator", "malware-author",
            "sponsor"));

    //
    // Getters and Setters
    //

    public Set<String> getAllTerms() {
        return terms;
    }

}
