package io.digitalstate.stix.vocabulary.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.vocabulary.StixVocabulary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndustrySectors implements StixVocabulary {

    @JsonProperty("industry_sectors_vocabulary")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "agriculture", "aerospace", "automotive",
            "communications", "construction", "defence",
            "education", "energy", "entertainment",
            "financial-services", "government-national", "government-regional",
            "government-local", "government-public-services", "healthcare",
            "hospitality-leisure", "infrastructure", "insurance",
            "manufacturing", "mining", "non-profit",
            "pharmaceuticals", "retail", "technology",
            "telecommunications", "transportation", "utilities"));

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
