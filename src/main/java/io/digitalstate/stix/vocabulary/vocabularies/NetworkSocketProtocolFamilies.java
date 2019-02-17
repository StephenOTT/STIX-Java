package io.digitalstate.stix.vocabulary.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.vocabulary.StixVocabulary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NetworkSocketProtocolFamilies implements StixVocabulary {

    @JsonProperty("network-socket-protocol-family-enum")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "PF_INET", "PF_AX25", "PF_IPX", "PF_INET_6",
            "PF_APPLETALK", "PF_NETROM", "PF_BRIDGE", "PF_ATMPVC",
            "PF_X25", "PF_ROSE", "PF_DECNET", "PF_NETBEUI",
            "PF_SECURITY", "PF_KEY", "PF_NETLINK", "PF_ROUTE",
            "PF_PACKET", "PF_ASH", "PF_ECONET", "PF_ATMSVC",
            "PF_SNA", "PF_IRDA", "PF_PPPOX", "PF_WANPIPE",
            "PF_BLUETOOTH"
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
