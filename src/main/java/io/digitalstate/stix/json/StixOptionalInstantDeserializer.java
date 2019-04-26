package io.digitalstate.stix.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.common.StixInstant;

import java.io.IOException;
import java.util.Optional;

public class StixOptionalInstantDeserializer extends StdDeserializer<Optional<StixInstant>> {

    public StixOptionalInstantDeserializer(){
        this(null);
    }

    public StixOptionalInstantDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Optional<StixInstant> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return Optional.of(StixInstant.parse(p.getText()));
    }
}
