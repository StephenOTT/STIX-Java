package io.digitalstate.stix.graph.datamarkings;

import io.digitalstate.stix.datamarkings.MarkingDefinitionDm;
import io.digitalstate.stix.graph.GraphGenerator;
import io.digitalstate.stix.graph.elements.GraphElement;
import io.digitalstate.stix.graph.elements.Node;

import java.util.HashSet;
import java.util.Set;

public class MarkingDefinitionGraphGenerator implements GraphGenerator{

    private MarkingDefinitionDm object;

    public MarkingDefinitionGraphGenerator(MarkingDefinitionDm object) {
        this.object = object;
    }

    public MarkingDefinitionDm getObject() {
        return object;
    }

    public Set<GraphElement> process(){
        Set<GraphElement> elements = new HashSet<>();

        elements.add(generateNode());
//        elements.addAll(generateEdges());

        return elements;
    }

    private Node generateNode(){
        return new Node(object.getId(), object.getType(), null, object);
    }

//    private Set<Edge> generateEdges() {
//        Set<Edge> edges = new HashSet<>();
//
//        edges.addAll(generateObjectMarkingRefEdges(object.getObjectMarkingRefs()));
//
//        return edges;
//    }

}
