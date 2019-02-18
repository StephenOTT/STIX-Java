package io.digitalstate.stix.vocabulary.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.vocabulary.StixVocabulary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WindowsRegistryValueDataTypes implements StixVocabulary {

    @JsonProperty("data-types")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "REG_NONE", "REG_SZ", "REG_EXPAND_SZ",
            "REG_BINARY", "REG_DWORD", "REG_DWORD_BIG_ENDIAN",
            "REG_LINK", "REG_MULTI_SZ", "REG_RESOURCE_LIST",
            "REG_FULL_RESOURCE_DESCRIPTION", "REG_RESOURCE_REQUIREMENTS_LIST", "REG_QWORD",
            "REG_INVALID_TYPE"
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
