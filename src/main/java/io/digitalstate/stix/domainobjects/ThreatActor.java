package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.ThreatActorProperties;
import io.digitalstate.stix.vocabularies.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class ThreatActor extends ThreatActorProperties implements StixDomainObject {

    private static final String TYPE = "threat-actor";

    public ThreatActor(String name,
                       LinkedHashSet<String> threatActorLabels){

        setType(TYPE);
        setId(generateUuidAsString());
        setName(name);
        setLabels(threatActorLabels);
    }
    public ThreatActor(String name, String... threatActorLabels){
        this(name, new LinkedHashSet<>(Arrays.asList(threatActorLabels)));
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
     * @param labels labels are enforced with threatActorLabel vocabulary
     */
    @Override
    public void setLabels(LinkedHashSet<String> labels) {
        Set<String> threatActorLabels = Vocabularies.getThreatActorLabels();

        if (!labels.isEmpty() && labels.containsAll(threatActorLabels)){
            this.labels = labels;
        } else {
            throw new IllegalArgumentException("At least one label must be provided");
        }
    }
}
