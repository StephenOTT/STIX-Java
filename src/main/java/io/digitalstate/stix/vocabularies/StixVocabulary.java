package io.digitalstate.stix.vocabularies;

import io.digitalstate.stix.helpers.JsonConvertable;

import java.util.Set;

public interface StixVocabulary extends JsonConvertable {

    Set<String> getTerms();

    void setTerms(Set<String> terms);

    public boolean vocabularyContains(Set<String> value);

    public boolean vocabularyContains(String value);

    public StixVocabulary addTerms(Set<String> additionalTerms);

}
