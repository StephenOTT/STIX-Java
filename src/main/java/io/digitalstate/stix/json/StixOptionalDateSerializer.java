package io.digitalstate.stix.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

public class StixOptionalDateSerializer extends StdSerializer<Optional<Instant>> {

    public StixOptionalDateSerializer() {
        this(null);
    }

    public StixOptionalDateSerializer(Class<Optional<Instant>> t) {
        super(t);
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Optional<Instant> value) {
        return !value.isPresent();
    }

    @Override
    public void serialize(Optional<Instant> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(StixDataFormats.getStixDateTimeFormatter().format(value.orElseThrow(IllegalStateException::new)));
    }
}
