package io.digitalstate.stix.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.io.IOException;
import java.time.Instant;

public class StixDateSerializer extends StdSerializer<Instant> {

    public StixDateSerializer() {
        this(null);
    }

    public StixDateSerializer(Class<Instant> t) {
        super(t);
    }

    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(StixDataFormats.getStixDateTimeFormatter().format(value));
    }
}
