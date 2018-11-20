package io.digitalstate.stix.datamarkings.definitions;

import io.digitalstate.stix.datamarkings.StixDataMarking;
import io.digitalstate.stix.datamarkings.markingtypes.MarkingObjectType;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class MarkingDefinition extends MarkingDefinitionProperties implements StixDataMarking {

    private static final String TYPE = "marking-definition";

    public MarkingDefinition(MarkingObjectType markingObjectType){
        setType(TYPE);
        setId(generateUuidAsString());
        setDefinition(markingObjectType);
        setDefinitionType(markingObjectType.getType());
    }
}
