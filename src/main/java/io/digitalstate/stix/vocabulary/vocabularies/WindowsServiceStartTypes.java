package io.digitalstate.stix.vocabulary.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.vocabulary.StixVocabulary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WindowsServiceStartTypes implements StixVocabulary {

    @JsonProperty("windows-service-start-type-enum")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "SERVICE_AUTO_START", "SERVICE_BOOT_START", "SERVICE_DEMAND_START",
            "SERVICE_DISABLED", "SERVICE_SYSTEM_ALERT"
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
