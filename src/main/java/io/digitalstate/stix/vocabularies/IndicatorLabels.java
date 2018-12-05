package io.digitalstate.stix.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class IndicatorLabels implements StixVocabulary {

    @JsonProperty("indicator_labels_vocabulary")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "anomalous-activity", "anonymization", "benign",
            "compromised", "malicious-activity", "attribution"));

    //
    // Getters and Setters
    //

    public Set<String> getAllTerms() {
        return terms;
    }

}
