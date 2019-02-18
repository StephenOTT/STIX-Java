package io.digitalstate.stix.vocabulary.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.vocabulary.StixVocabulary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WindowsServiceStatuses implements StixVocabulary {

    @JsonProperty("windows-service-status-enum")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "SERVICE_CONTINUE_PENDING", "SERVICE_PAUSE_PENDING", "SERVICE_PAUSED",
            "SERVICE_RUNNING", "SERVICE_START_PENDING", "SERVICE_STOP_PENDING",
            "SERVICE_STOPPED"
    ));

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
