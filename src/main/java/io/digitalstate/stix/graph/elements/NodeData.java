package io.digitalstate.stix.graph.elements;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.HashMap;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

public class  NodeData {

    private String id;
    private String type;
    private String parent;
    private Object jsonData;
    private HashMap<String, Object> additionalProperties = new HashMap<>();

    public NodeData(String id, String type, String parent, Object jsonData) {
        this.id = id;
        this.type = type;
        this.parent = parent;
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

    @JsonProperty("parent")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @JsonProperty("stix")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    public Object getJsonData() {
        return jsonData;
    }

    public void setJsonData(Object jsonData) {
        this.jsonData = jsonData;
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
