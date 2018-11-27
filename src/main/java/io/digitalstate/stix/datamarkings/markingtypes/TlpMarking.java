package io.digitalstate.stix.datamarkings.markingtypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.vocabularies.TlpLevels;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@JsonDeserialize(using = TlpMarking.Deserializer.class)
@JsonPropertyOrder({"tlp"})
public class TlpMarking extends MarkingObjectTypeCommonProperties implements MarkingObjectType {

    private static final String type = "tlp";

    private String tlp;

    private TlpLevels tlpLevelsVocab = new TlpLevels();

    public TlpMarking(String tlpValue){
        setTlp(tlpValue);
    }

    //
    // Getters and Setters
    //

    @Override
    @JsonIgnore
    public String getType() {
        return type;
    }

    public String getTlp() {
        return tlp;
    }

    @JsonIgnore
    public TlpLevels getTlpLevelsVocab() {
        return tlpLevelsVocab;
    }

    @JsonIgnore
    public void setTlpLevelsVocab(TlpLevels tlpLevelsVocab) {
        Objects.requireNonNull(tlpLevelsVocab, "tlpLevelsVocab cannot be null");
        this.tlpLevelsVocab = tlpLevelsVocab;
    }

    public void setTlp(String tlp) {
        if (tlpLevelsVocab.vocabularyContains(tlp)) {
            this.tlp = tlp;
        } else {
            throw new IllegalArgumentException("TLP value (" + tlp + ")" + " is not a valid TLP level");
        }
    }


    /**
     * Used for JSON Deserialization
     */
    private TlpMarking() {
    }

    public static class Deserializer extends StdDeserializer<TlpMarking> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public TlpMarking deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            TlpMarking object = new TlpMarking();

            Optional<JsonNode> tlp = Optional.ofNullable(node.get("tlp"));
            tlp.ifPresent(o -> {
                object.setTlp(o.textValue());
            });
            tlp.orElseThrow(()-> new IllegalArgumentException("tlp is required"));

            return object;
        }
    }

}
