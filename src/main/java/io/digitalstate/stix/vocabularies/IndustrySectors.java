package io.digitalstate.stix.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    public Set<String> getTerms() {
        return terms;
    }

    public void setTerms(Set<String> terms) {
        Objects.requireNonNull(terms, "terms cannot be null");
        this.terms = terms;
    }

    public boolean vocabularyContains(Set<String> value){
        Objects.requireNonNull(value, "value cannot be null");
        return getTerms().containsAll(value);
    }

    public boolean vocabularyContains(String... value){
        Objects.requireNonNull(value, "value cannot be null");
        return vocabularyContains(new HashSet<>(Arrays.asList(value)));
    }

    public boolean vocabularyContains(String value){
        Objects.requireNonNull(value, "value cannot be null");
        return vocabularyContains(new HashSet<>(Arrays.asList(value)));
    }

    public IndustrySectors addTerms(Set<String> additionalTerms){
        Objects.requireNonNull(additionalTerms, "additionalTerms cannot be null");
        getTerms().addAll(additionalTerms);
        return this;
    }

    @Override
    public String toJsonString() throws JsonProcessingException {
        return StixDataFormats.getJsonMapper().writeValueAsString(getTerms());
    }
}
