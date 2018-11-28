package io.digitalstate.stix.datamarkings.granular;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
import io.digitalstate.stix.relationshipobjects.Relation;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;

@JsonDeserialize(using = GranularMarking.Deserializer.class)
@JsonPropertyOrder({"marking_ref", "selectors"})
public class GranularMarking {

    private Relation<MarkingDefinition> markingRef;

    //@TODO Convert selectors from String into its own object that validates proper Selector formats
    private LinkedHashSet<String> selectors;


    public GranularMarking(Relation<MarkingDefinition> markingDefinition, LinkedHashSet<String> selectors) {
        setMarkingRef(markingDefinition);
        setSelectors(selectors);
    }

    public GranularMarking(Relation<MarkingDefinition> markingDefinition, String... selectors) {
        this(markingDefinition, new LinkedHashSet<>(Arrays.asList(selectors)));
    }

    public GranularMarking(MarkingDefinition markingDefinition, String... selectors) {
        this(new Relation<>(markingDefinition), new LinkedHashSet<>(Arrays.asList(selectors)));
    }

    //
    // Getters and Setters
    //

    @JsonIgnore
    public Relation<MarkingDefinition> getMarkingRef() {
        return markingRef;
    }

    public void setMarkingRef(Relation<MarkingDefinition> markingRef) {
        this.markingRef = markingRef;
    }

    @JsonProperty("marking_ref")
    public String getMarkingRefId() {
        if (getMarkingRef().hasObject()) {
            return getMarkingRef().getObject().getId();
        } else {
            return getMarkingRef().getId();
        }
    }

    public LinkedHashSet<String> getSelectors() {
        return selectors;
    }

    public void setSelectors(LinkedHashSet<String> selectors) {
        this.selectors = selectors;
    }


    /**
     * Used for JSON Deserialization
     */
    private GranularMarking() {
    }

    public static class Deserializer extends StdDeserializer<GranularMarking> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public GranularMarking deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            GranularMarking object = new GranularMarking();

            // MarkingRef
            Optional<JsonNode> marking_ref = Optional.ofNullable(node.get("marking_ref"));
            marking_ref.ifPresent(o -> {
                if (o.isTextual()) {
                    Relation<MarkingDefinition> relation = new Relation<MarkingDefinition>(o.asText());
                    object.setMarkingRef(relation);
                } else {
                    throw new IllegalArgumentException("marking_ref must be a reference id string");
                }
            });
            marking_ref.orElseThrow(() -> new IllegalArgumentException("marking_ref is Required"));

            // Selectors
            Optional<JsonNode> selectors = Optional.ofNullable(node.get("selectors"));
            selectors.ifPresent(o -> {
                if (o.isArray()) {
                    LinkedHashSet<String> selectorsSet = new LinkedHashSet<>();
                    o.forEach(selector->{
                        selectorsSet.add(selector.asText());
                    });
                    object.setSelectors(selectorsSet);
                } else {
                    throw new IllegalArgumentException("selectors must be a array of strings");
                }
            });
            selectors.orElseThrow(() -> new IllegalArgumentException("selectors is Required"));

            return object;
        }
    }
}
