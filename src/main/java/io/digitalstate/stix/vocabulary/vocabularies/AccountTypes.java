package io.digitalstate.stix.vocabulary.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.vocabulary.StixVocabulary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountTypes implements StixVocabulary {
	@JsonProperty("account-type-ov")
	private Set<String> terms = new HashSet<>(Arrays.asList(
			"unix", "windows local", "windows domain", "ldap", "tacacs", "radius",
			"nis", "openid", "facebook", "skype", "twitter", "kavi"
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
