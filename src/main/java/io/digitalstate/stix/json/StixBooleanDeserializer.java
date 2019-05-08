package io.digitalstate.stix.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.common.StixBoolean;

import java.io.IOException;

public class StixBooleanDeserializer extends StdDeserializer<StixBoolean> {

    public StixBooleanDeserializer(){
        this(null);
    }

    public StixBooleanDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public StixBoolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return new StixBoolean(p.getBooleanValue());
    }
}
