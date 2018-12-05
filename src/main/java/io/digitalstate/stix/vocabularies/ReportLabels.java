package io.digitalstate.stix.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportLabels implements StixVocabulary {

    @JsonProperty("report_labels_vocabulary")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "threat-report", "attack-pattern", "campaign",
            "identity", "indicator", "malware",
            "observed-data", "threat-actor", "tool",
            "vulnerability"));

    //
    // Getters and Setters
    //

    public Set<String> getAllTerms() {
        return terms;
    }

    @Override
    public Set<String> getAllTermsWithAdditional(String[] terms) {
        return Stream.concat(getAllTerms().stream(), Arrays.stream(terms))
                .collect(Collectors.toCollection(HashSet::new));
    }
}
