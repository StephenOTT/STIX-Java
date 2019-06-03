package io.digitalstate.stix.graph;

import io.digitalstate.stix.graph.elements.GraphElement;
import io.digitalstate.stix.graph.elements.Node;
import io.digitalstate.stix.sdo.DomainObject;

import java.util.HashSet;
import java.util.Set;

public class DomainObjectGraphGenerator implements GraphGenerator {

    private DomainObject object;

    public DomainObjectGraphGenerator(DomainObject object) {
        this.object = object;
    }

    @Override
    public Set<GraphElement> process() {
        Set<GraphElement> elements = new HashSet<>();

        elements.add(generateNode());

        return elements;
    }

    public DomainObject getObject() {
        return object;
    }

    private Node generateNode() {
        return new Node(object.getId(), object.getType(), null, object);
    }

}
