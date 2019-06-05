package io.digitalstate.stix.graph.coo;

import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.graph.GraphGenerator;
import io.digitalstate.stix.graph.elements.GraphElement;
import io.digitalstate.stix.graph.elements.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Generally used by the Observed Data SDO Graph Generator
 */
public class CyberObservableGraphGenerator implements GraphGenerator {

    private CyberObservableObject object;
    private String observedDataObjectId;

    public CyberObservableGraphGenerator(String observedDataObjectId, CyberObservableObject object) {
        this.object = object;
        this.observedDataObjectId = observedDataObjectId;
    }

    public CyberObservableObject getObject() {
        return object;
    }

    public String getObservedDataObjectId() {
        return observedDataObjectId;
    }

    public Set<GraphElement> process(){
        Set<GraphElement> elements = new HashSet<>();
        elements.add(generateNode());

        return elements;
    }

    // Is public to support custom usage by Observed Data Graph Generator
    public Node generateNode(){
        String uuid = object.getObservableObjectKey() + "--" + UUID.randomUUID().toString();
        String type = "coo-" + object.getType();

        return new Node(uuid, type, null, object);
        //@TODO Refactor to support the parent node prob for sub graph node support
//        return new Node(uuid, type, observedDataObjectId, object);
    }

}
