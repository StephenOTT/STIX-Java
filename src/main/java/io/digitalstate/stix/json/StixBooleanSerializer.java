package io.digitalstate.stix.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.digitalstate.stix.common.StixBoolean;

import java.io.IOException;

public class StixBooleanSerializer extends StdSerializer<StixBoolean> {

    public StixBooleanSerializer() {
        this(null);
    }

    public StixBooleanSerializer(Class<StixBoolean> t) {
        super(t);
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, StixBoolean value) {
        return !value.isdefinedValue();
        //@TODO Future enhancement: Make this a configuration so the impl can decide if non defined values should still be serialized as their default values
    }

    @Override
    public void serialize(final StixBoolean value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeBoolean(value.getStixBooleanValue());
    }
}
