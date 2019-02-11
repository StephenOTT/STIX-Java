package io.digitalstate.stix.coo.json.observables;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.digitalstate.stix.coo.CyberObservableObject;

import java.io.IOException;
import java.util.Set;

public class CyberObservableSetFieldSerializer extends StdSerializer<Set<CyberObservableObject>> {

    public CyberObservableSetFieldSerializer() {
        this(null);
    }

    protected CyberObservableSetFieldSerializer(Class<Set<CyberObservableObject>> t) {
        super(t);
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Set<CyberObservableObject> value) {
        return value.isEmpty();
    }

    @Override
    public void serialize(Set<CyberObservableObject> values, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        values.forEach(observableObject -> {
            try {
                gen.writeObjectField(observableObject.getObservableObjectKey(), observableObject);
            } catch (IOException e) {
                throw new IllegalStateException("Unable to serialize object", e);
            }
        });
        gen.writeEndObject();
    }
}
