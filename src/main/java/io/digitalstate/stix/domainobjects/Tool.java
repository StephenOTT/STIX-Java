package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.ToolProperties;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class Tool extends ToolProperties implements StixDomainObject {

    private static final String TYPE = "tool";

    public Tool(String name,
                LinkedHashSet<String> toolLabels){

        setType(TYPE);
        setId(generateUuidAsString());
        setName(name);
        setLabels(toolLabels);
    }
    public Tool(String name, String... toolLabels){
        this(name, new LinkedHashSet<>(Arrays.asList(toolLabels)));
    }

}
