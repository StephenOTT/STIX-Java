package io.digitalstate.stix.vocabulary.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.vocabulary.StixVocabulary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NetworkSocketAddressFamilies implements StixVocabulary {

    @JsonProperty("network-socket-address-family-enum")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "AF_UNSPEC", "AF_INET", "AF_IPX", "AF_APPLETALK",
            "AF_NETBIOS", "AF_INET_6", "AF_IRDA", "AF_BTH"
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
