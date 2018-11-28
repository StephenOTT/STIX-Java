package io.digitalstate.stix.types;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.types.ExternalReference;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@JsonDeserialize(using = Hashes.Deserializer.class)
@JsonPropertyOrder({"hashes"})
public class Hashes {

    private  Map<String,String> hashes = new HashMap<>();

    public Map getMap() {
        return hashes;
    }

    public void setMap(Map<String,String> hashes) {
        this.hashes = hashes;
    }

    public Hashes(Map<String,String> hashes){
        setMap(hashes);
    }


    @Override
    public String toString(){
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(this);
        return builder.toString();
    }


    /**
     * Used for JSON Deserialization
     */
    private Hashes(){}

    public static class Deserializer extends StdDeserializer<Hashes> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Hashes deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            Hashes object = new Hashes();
            HashMap<String, String> hashes = new HashMap<>();

            node.fieldNames().forEachRemaining(field->{
                JsonNode hashField = node.get(field);
                hashes.put(field, hashField.asText());
            });

            object.setMap(hashes);

            return object;
        }
    }
}
