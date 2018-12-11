package io.digitalstate.stix.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashingAlgorithms implements StixVocabulary {

    @JsonProperty("hashing_algorithms_vocabulary")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "MD5", "MD6", "RIPEMD-160", "SHA-1",
            "SHA-224", "SHA-256", "SHA-384", "SHA-512",
            "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512",
            "SSDEEP", "WHIRLPOOL"));


    public Set<String> getAllTerms() {
        return terms;
    }

    @Override
    public Set<String> getAllTermsWithAdditional(String[] terms) {
        return Stream.concat(getAllTerms().stream(), Arrays.stream(terms))
                .collect(Collectors.toCollection(HashSet::new));
    }

}
