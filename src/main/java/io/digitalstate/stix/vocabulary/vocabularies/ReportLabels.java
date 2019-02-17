package io.digitalstate.stix.vocabulary.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.vocabulary.StixVocabulary;

import java.util.Arrays;
import java.util.HashSet;
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
