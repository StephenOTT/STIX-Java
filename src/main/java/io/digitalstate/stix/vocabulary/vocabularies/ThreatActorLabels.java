package io.digitalstate.stix.vocabulary.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.vocabulary.StixVocabulary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreatActorLabels implements StixVocabulary {

    @JsonProperty("threat_actor_labels_vocabulary")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "activist", "competitor", "crime-syndicate",
            "criminal", "hacker", "insider-accidental",
            "insider-disgruntled", "nation-state", "sensationalist",
            "spy", "terrorist"));

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
