package io.digitalstate.stix.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.io.IOException;
import java.time.Instant;

public class StixDateDeserializer extends StdDeserializer<Instant> {

    public StixDateDeserializer(){
        this(null);
    }

    public StixDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String value = p.getText();
        return Instant.from(StixDataFormats.getStixDateTimeFormatter().parse(value));
    }
}
