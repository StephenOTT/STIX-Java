package io.digitalstate.stix.datamarkings.markingtypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@JsonDeserialize(using = StatementMarking.Deserializer.class)
@JsonPropertyOrder({"statement"})
public class StatementMarking extends MarkingObjectTypeCommonProperties implements MarkingObjectType {

    private static final String type = "statement";

    private String statement;


    public StatementMarking(String statementValue){
        setStatement(statementValue);
    }

    //
    // Getters and Setters
    //

    @Override
    @JsonIgnore
    public String getType() {
        return type;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        Objects.requireNonNull(statement, "statement cannot be null");
        this.statement = statement;
    }


    /**
     * Used for JSON Deserialization
     */
    private StatementMarking() {
    }

    public static class Deserializer extends StdDeserializer<StatementMarking> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public StatementMarking deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            StatementMarking object = new StatementMarking();

            Optional<JsonNode> statement = Optional.ofNullable(node.get("statement"));
            statement.ifPresent(o -> {
                object.setStatement(o.textValue());
            });
            statement.orElseThrow(()-> new IllegalArgumentException("statement is required"));

            return object;
        }
    }
}
