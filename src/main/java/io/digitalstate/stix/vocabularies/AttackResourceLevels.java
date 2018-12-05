package io.digitalstate.stix.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AttackResourceLevels implements StixVocabulary {

    @JsonProperty("attack_resource_levels_vocabulary")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "individual", "club", "content",
            "team", "organization", "government"));

    //
    // Getters and Setters
    //

    public Set<String> getAllTerms() {
        return terms;
    }

}
