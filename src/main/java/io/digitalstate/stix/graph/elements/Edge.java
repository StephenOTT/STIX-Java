package io.digitalstate.stix.graph.elements;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Edge implements GraphElement {

    private EdgeData data;

    public Edge(@NotNull String id, String type, String source, String target, Object jsonData) {
        this.data = new EdgeData(id, type, source, target, jsonData);
    }

    @JsonProperty("data")
    public EdgeData getData() {
        return data;
    }

    public void setData(EdgeData data) {
        this.data = data;
    }
}


