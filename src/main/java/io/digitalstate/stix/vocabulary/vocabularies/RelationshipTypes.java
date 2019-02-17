package io.digitalstate.stix.vocabulary.vocabularies;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RelationshipTypes implements StixVocabulary {

    private static Set<String> terms = new HashSet<>(Arrays.asList(
                "targets",
                "uses",
                "attributed-to",
                "mitigates",
                "indicates",
                "variant-of",
                "impersonates"));

    private static Set<String> commonTerms = new HashSet<>(Arrays.asList(
                "duplicate-of",
                "derived-from",
                "related-to"));

    @Override
    public Set<String> getAllTerms() {
        return Stream.concat(terms.stream(), commonTerms.stream())
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Set<String> getAllTermsWithAdditional(String[] terms) {
        return Stream.concat(getAllTerms().stream(), Arrays.stream(terms))
                .collect(Collectors.toCollection(HashSet::new));
    }
}
