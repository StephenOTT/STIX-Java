package io.digitalstate.stix.vocabulary.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.vocabulary.StixVocabulary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WindowsServiceTypes implements StixVocabulary {

    @JsonProperty("windows-service-type-enum")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "SERVICE_KERNEL_DRIVER", "SERVICE_FILE_SYSTEM_DRIVER",
            "SERVICE_WIN32_OWN_PROCESS", "SERVICE_WIN32_SHARE_PROCESS"
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
