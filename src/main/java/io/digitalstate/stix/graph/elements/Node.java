package io.digitalstate.stix.graph.elements;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Node implements GraphElement {

    private NodeData data;

    public Node(@NotNull String id, String type,  String parent, Object jsonData) {
        this.data = new NodeData(id, type, parent, jsonData);
    }

    @JsonProperty("data")
    public NodeData getData() {
        return data;
    }

    public void setData(NodeData data) {
        this.data = data;
    }
}
