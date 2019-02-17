package io.digitalstate.stix.vocabulary.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NetworkSocketTypes implements StixVocabulary {

    @JsonProperty("network-socket-type-enum")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "SOCK_STREAM", "SOCK_DGRAM",
            "SOCK_RAW", "SOCK_RDM",
            "SOCK_SEQPACKET"
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
