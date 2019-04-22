package io.digitalstate.stix.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

public class StixOptionalDateDeserializer extends StdDeserializer<Optional<Instant>> {

    public StixOptionalDateDeserializer(){
        this(null);
    }

    public StixOptionalDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Optional<Instant> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String value = p.getText();
        return Optional.of(Instant.from(StixDataFormats.getStixDateTimeFormatter().parse(value)));
    }
}
