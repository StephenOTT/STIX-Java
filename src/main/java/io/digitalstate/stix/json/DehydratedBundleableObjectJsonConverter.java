package io.digitalstate.stix.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.digitalstate.stix.bundle.BundleableObject;

/**
 * Generates a Dehydrated Bundleable Object based on a ID.
 */
public class DehydratedBundleableObjectJsonConverter extends StdConverter<String, BundleableObject> {

    @Override
    public BundleableObject convert(String value) {
            String[] parsedValue = value.split("--");

            if (parsedValue.length == 2){
                ObjectMapper mapper = StixParsers.getJsonMapper(true);
                ObjectNode node = mapper.createObjectNode();

                node.put("type", parsedValue[0]);
                node.put("id", value);
                node.put("hydrated", false);


                try {
                    BundleableObject bundleableObject =  mapper.treeToValue(node, BundleableObject.class);
                    //@TODO add more logic
                    return bundleableObject;

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    throw new IllegalArgumentException("Cannot Parse Json: " + e.getMessage());
                }

            } else {
                throw new IllegalArgumentException("Id is not valid format, Cannot Parse Value: " + value);
        }
    }
}