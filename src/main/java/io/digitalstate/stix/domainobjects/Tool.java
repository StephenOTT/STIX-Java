package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.ToolProperties;
import io.digitalstate.stix.vocabularies.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @Override
    public void setName(String name) {
        if (StringUtils.isNotBlank(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }

    /**
     *
     * @param labels labels are enforced with toolLabel vocabulary
     */
    @Override
    public void setLabels(LinkedHashSet<String> labels) {
        Set<String> toolLabels = Vocabularies.getToolLabel();

        if (!labels.isEmpty() && labels.containsAll(toolLabels)){
            this.labels = labels;
        } else {
            throw new IllegalArgumentException("At least one label must be provided");
        }
    }
}
