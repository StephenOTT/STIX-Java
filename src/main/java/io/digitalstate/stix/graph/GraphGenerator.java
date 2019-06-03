package io.digitalstate.stix.graph;

import io.digitalstate.stix.graph.elements.GraphElement;

import java.util.Set;

public interface GraphGenerator {

    Set<GraphElement> process();
}
