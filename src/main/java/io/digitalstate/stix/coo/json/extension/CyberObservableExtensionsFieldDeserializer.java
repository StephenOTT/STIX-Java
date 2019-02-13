package io.digitalstate.stix.coo.json.extension;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.digitalstate.stix.coo.extension.CyberObservableExtension;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CyberObservableExtensionsFieldDeserializer extends StdDeserializer<Set<CyberObservableExtension>> {

    public CyberObservableExtensionsFieldDeserializer() {
        this(null);
    }

    protected CyberObservableExtensionsFieldDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Set<CyberObservableExtension> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Set<CyberObservableExtension> extensions = new HashSet<>();

        TreeNode tree = p.readValueAsTree();

        tree.fieldNames().forEachRemaining(f -> {
            ObjectNode node = (ObjectNode) tree.get(f);
            node.put("type", f);
            try {
                CyberObservableExtension extension = node.traverse(p.getCodec()).readValueAs(CyberObservableExtension.class);
                extensions.add(extension);
            } catch (IOException e) {
                throw new IllegalStateException("Cannot deserialize COO extension: ", e);
            }
        });

        return extensions;
    }
}
