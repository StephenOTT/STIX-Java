package io.digitalstate.stix.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.digitalstate.stix.common.StixInstant;

import java.io.IOException;

public class StixInstantSerializer extends StdSerializer<StixInstant> {

    public StixInstantSerializer() {
        this(null);
    }

    public StixInstantSerializer(Class<StixInstant> t) {
        super(t);
    }

    @Override
    public void serialize(final StixInstant value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.toString());
    }
}
