package io.digitalstate.stix.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.digitalstate.stix.common.StixInstant;

import java.io.IOException;
import java.util.Optional;

public class StixOptionalInstantSerializer extends StdSerializer<Optional<StixInstant>> {

    public StixOptionalInstantSerializer() {
        this(null);
    }

    public StixOptionalInstantSerializer(Class<Optional<StixInstant>> t) {
        super(t);
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Optional<StixInstant> value) {
        return !value.isPresent();
    }

    @Override
    public void serialize(Optional<StixInstant> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (!value.isPresent()){
            throw new IllegalStateException();
        } else {
            gen.writeString(value.get().toString());
        }
    }
}
