package io.digitalstate.stix.coo.json.extension;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.digitalstate.stix.coo.extension.CyberObservableExtension;

import java.io.IOException;
import java.util.Set;

public class CyberObservableExtensionsFieldSerializer extends StdSerializer<Set<CyberObservableExtension>> {

    public CyberObservableExtensionsFieldSerializer() {
        this(null);
    }

    protected CyberObservableExtensionsFieldSerializer(Class<Set<CyberObservableExtension>> t) {
        super(t);
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, Set<CyberObservableExtension> value) {
        return value.isEmpty();
    }

    @Override
    public void serialize(Set<CyberObservableExtension> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        value.forEach(ext -> {
            try {
                gen.writeObjectField(ext.getType(), ext);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        gen.writeEndObject();
    }
}
