package io.digitalstate.stix.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.digitalstate.stix.sdo.DomainObject;

import java.util.Optional;

/**
 * Generates a Dehydrated Domain Object based on a ID.
 */
public class DehydratedOptionalDomainObjectJsonConverter extends StdConverter<String, Optional<DomainObject>> {

    @Override
    public Optional<DomainObject> convert(String value) {
            String[] parsedValue = value.split("--");

            if (parsedValue.length == 2){
                ObjectMapper mapper = StixParsers.getJsonMapper(true);
                ObjectNode node = mapper.createObjectNode();

                node.put("type", parsedValue[0]);
                node.put("id", value);
                node.put("hydrated", false);


                try {
                    return Optional.ofNullable(mapper.treeToValue(node, DomainObject.class));
                    //@TODO add more logic

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    throw new IllegalArgumentException("Cannot Parse Json: " + e.getMessage());
                }

            } else {
                throw new IllegalArgumentException("Id is not valid format, Cannot Parse Value: " + value);
        }
    }
}