package io.digitalstate.stix.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.common.StixInstant;

import java.io.IOException;

public class StixInstantDeserializer extends StdDeserializer<StixInstant> {

    public StixInstantDeserializer(){
        this(null);
    }

    public StixInstantDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public StixInstant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return StixInstant.parse(p.getText());
    }
}
