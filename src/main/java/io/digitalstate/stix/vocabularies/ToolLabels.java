package io.digitalstate.stix.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToolLabels implements StixVocabulary {

    @JsonProperty("tool_labels_vocabulary")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "denial-of-service", "exploitation", "information-gathering",
            "network-capture", "credential-exploitation", "remote-access",
            "vulnerability-scanning"));

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
