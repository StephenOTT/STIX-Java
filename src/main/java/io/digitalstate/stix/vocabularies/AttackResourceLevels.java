package io.digitalstate.stix.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AttackResourceLevels implements StixVocabulary {

    @JsonProperty("attack_resource_levels_vocabulary")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "individual", "club", "content",
            "team", "organization", "government"));

    //
    // Getters and Setters
    //

    @Override
    public Set<String> getAllTerms() {
        return terms;
    }

    @Override
    public Set<String> getAllTermsWithAdditional(String[] terms) {
        return Stream.concat(getAllTerms().stream(), Arrays.stream(terms))
                .collect(Collectors.toCollection(HashSet::new));
    }
}
