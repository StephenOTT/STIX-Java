package io.digitalstate.stix.graph;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.graph.elements.GraphElement;
import io.digitalstate.stix.json.StixParsers;

import java.util.HashSet;
import java.util.Set;

public class StixGraphGenerator implements GraphGenerator {

    private BundleObject bundle;
    private ObjectMapper jsonMapper = StixParsers.getJsonMapper();

    public StixGraphGenerator(BundleObject bundle) {
        this.bundle = bundle;
    }

    @Override
    public Set<GraphElement> process(){
        Set<GraphElement> elements = new HashSet<>();

        elements.addAll(new BundleObjectGraphGenerator(bundle).process());

        return elements;
    }

    public String toJson(){
        try {
            return jsonMapper.writeValueAsString(process());
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}
