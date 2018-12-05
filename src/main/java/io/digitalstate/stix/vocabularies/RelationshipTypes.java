package io.digitalstate.stix.vocabularies;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RelationshipTypes implements StixVocabulary {

    public Set<String> objectSpecificTerms = new HashSet<>(Arrays.asList(
                "targets",
                "uses",
                "attributed-to",
                "mitigates",
                "indicates",
                "variant-of",
                "impersonates"));

    public Set<String> commonTerms = new HashSet<>(Arrays.asList(
                "duplicate-of",
                "derived-from",
                "related-to"));


    public Set<String> getAllTerms() {
        return Stream.concat(commonTerms.stream(), objectSpecificTerms.stream())
                .collect(Collectors.toCollection(HashSet::new));
    }
}
