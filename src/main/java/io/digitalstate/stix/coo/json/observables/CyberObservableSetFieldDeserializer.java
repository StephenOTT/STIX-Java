package io.digitalstate.stix.coo.json.observables;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.digitalstate.stix.coo.CyberObservableObject;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CyberObservableSetFieldDeserializer extends StdDeserializer<Set<CyberObservableObject>> {

    public CyberObservableSetFieldDeserializer() {
        this(null);
    }

    protected CyberObservableSetFieldDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Set<CyberObservableObject> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Set<CyberObservableObject> objects = new HashSet<>();
        TreeNode tree = p.readValueAsTree();

        tree.fieldNames().forEachRemaining(f->{
           ObjectNode node =  (ObjectNode)tree.get(f);
           node.put("observable_object_key", f);
//           System.out.println(node.toString());
            try {
                CyberObservableObject object = node.traverse(p.getCodec())
                        .readValueAs(CyberObservableObject.class);
                objects.add(object);

            } catch (IOException e) {
                throw new IllegalStateException("Unable to deserialize cyber observable object", e);
            }
        });

        return objects;
    }
}
