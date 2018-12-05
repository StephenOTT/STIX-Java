package io.digitalstate.stix.vocabularies;

import java.util.Set;

public interface StixVocabulary {

    /**
     * Get all default terms
     * @return
     */
    Set<String> getAllTerms();

    /**
     * Get all default terms and append some additional terms
     * @param terms
     * @return
     */
    Set<String> getAllTermsWithAdditional(String[] terms);
}
