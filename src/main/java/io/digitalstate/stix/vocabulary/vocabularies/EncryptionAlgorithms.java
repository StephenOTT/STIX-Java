package io.digitalstate.stix.vocabulary.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.vocabulary.StixVocabulary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EncryptionAlgorithms implements StixVocabulary {

	@JsonProperty("encryption-algo-ov")
	private Set<String> terms = new HashSet<>(Arrays.asList(
		"AES128-ECB", "AES128-CBC", "AES128-CFB", "AES128-COFB",
		"AES128-CTR", "AES128-XTS", "AES128-GCM",
		"Salsa20", "Salsa8B", "ChaCha20-Poly1305", "ChaCha20",
		 "DES-CBC", "3DES-CBC", "DES-EBC", "3DES-EBC", 
		 "CAST128-CBC", "CAST256-CBC", "RSA", "DSA"
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
