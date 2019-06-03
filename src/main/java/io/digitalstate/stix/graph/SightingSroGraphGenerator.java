package io.digitalstate.stix.graph;

import io.digitalstate.stix.graph.elements.Edge;
import io.digitalstate.stix.graph.elements.GraphElement;
import io.digitalstate.stix.graph.elements.Node;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.objects.ObservedDataSdo;
import io.digitalstate.stix.sro.objects.SightingSro;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SightingSroGraphGenerator implements GraphGenerator{

    private SightingSro object;

    public SightingSroGraphGenerator(SightingSro object) {
        this.object = object;
    }

    public SightingSro getObject() {
        return object;
    }

    public Set<GraphElement> process(){
        Set<GraphElement> elements = new HashSet<>();

        elements.add(generateNode());
        elements.addAll(generateEdges());

        return elements;
    }

    private Node generateNode(){
        return new Node(object.getId(), object.getType(), null, object);
    }

    private Set<Edge> generateEdges() {
        Set<Edge> edges = new HashSet<>();

        edges.add(generateSightingOfRefEdge(object.getSightingOfRef()));

        edges.addAll(generateObservedDataEdges(object.getId(), object.getObservedDataRefs()));

        return edges;
    }

    private Edge generateSightingOfRefEdge(DomainObject sightingOfRefDomainObject){
        String uuidPrefix = "ref";

        DomainObject sor = object.getSightingOfRef();

        String sorUuid =  uuidPrefix + "-" + UUID.randomUUID().toString();

        Edge edge = new Edge(sorUuid, uuidPrefix, object.getId(), sor.getId(), null);

        edge.getData().setEdgeLabel("sighting-of");

        edge.getData().getAdditionalProperties().put("ref_type", "sighting_of_ref");

        return edge;
    }

    private Set<Edge> generateObservedDataEdges(String sourceId, Set<ObservedDataSdo> observedDataSdoSet){
        Set<Edge> edges = new HashSet<>();

        observedDataSdoSet.forEach(od -> {
            String uuidPrefix = "ref";
            String odUuid =  uuidPrefix + "-" + UUID.randomUUID().toString();

            Edge edge = new Edge(odUuid, uuidPrefix, sourceId, od.getId(), null);

            edge.getData().setEdgeLabel("observed-data");

            edge.getData().getAdditionalProperties().put("ref_type", "observed_data");

            edges.add(edge);
        });

        return edges;
    }

}
