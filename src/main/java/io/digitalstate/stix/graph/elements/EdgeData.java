package io.digitalstate.stix.graph.elements;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.HashMap;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

public class EdgeData {

    private String id;
    private String type;
    private String source;
    private String target;
    private Object jsonData;
    private String edgeLabel;
    private HashMap<String, Object> additionalProperties = new HashMap<>();

    public EdgeData(String id, String type, String source, String target, Object jsonData) {
        this.id = id;
        this.type = type;
        this.source = source;
        this.target = target;
        this.jsonData = jsonData;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("target")
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @JsonProperty("stix")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    public Object getJsonData() {
        return jsonData;
    }

    public void setJsonData(Object jsonData) {
        this.jsonData = jsonData;
    }

    @JsonProperty("label")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    public String getEdgeLabel() {
        return edgeLabel;
    }

    public void setEdgeLabel(String edgeLabel) {
        this.edgeLabel = edgeLabel;
    }

    @JsonUnwrapped
    @JsonAnyGetter
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    public HashMap<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(HashMap<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
