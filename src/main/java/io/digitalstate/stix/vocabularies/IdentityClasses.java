package io.digitalstate.stix.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class IdentityClasses implements StixVocabulary {

    @JsonProperty("identity_classes_vocabulary")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "individual", "group", "organization",
            "class", "unknown"));

    //
    // Getters and Setters
    //

    public Set<String> getAllTerms() {
        return terms;
    }

}
