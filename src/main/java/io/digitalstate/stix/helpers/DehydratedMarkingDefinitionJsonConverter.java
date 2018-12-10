package io.digitalstate.stix.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.digitalstate.stix.datamarkings.MarkingDefinitionDm;
import io.digitalstate.stix.sdo.DomainObject;

/**
 * Generates a Dehydrated Domain Object based on a ID.
 */
public class DehydratedMarkingDefinitionJsonConverter extends StdConverter<String, MarkingDefinitionDm> {

    @Override
    public MarkingDefinitionDm convert(String value) {
            String[] parsedValue = value.split("--");

            if (parsedValue.length == 2){
                ObjectMapper mapper = StixDataFormats.getJsonMapper(true);
                ObjectNode node = mapper.createObjectNode();

                node.put("type", parsedValue[0]);
                node.put("id", value);
                node.put("hydrated", false);


                try {
                    MarkingDefinitionDm domainObject =  mapper.treeToValue(node, MarkingDefinitionDm.class);
                    //@TODO add more logic
                    return domainObject;

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    throw new IllegalArgumentException("Cannot Parse Json: " + e.getMessage());
                }

            } else {
                throw new IllegalArgumentException("Id is not valid format, Cannot Parse Value: " + value);
        }
    }
}