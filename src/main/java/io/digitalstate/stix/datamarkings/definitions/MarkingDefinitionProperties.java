package io.digitalstate.stix.datamarkings.definitions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.datamarkings.markingtypes.MarkingObjectType;
import io.digitalstate.stix.domainobjects.types.ExternalReference;
import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class MarkingDefinitionProperties {

    @JsonIgnore
    private final String specVersion = StixSpecVersion.SPECVERSION;

    protected String type;
    protected String id;

    @JsonProperty("created_by_ref")
    @JsonInclude(NON_NULL)
    protected String createdByRef = null;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime created = ZonedDateTime.now();

    @JsonProperty("external_references")
    @JsonInclude(NON_NULL)
    protected LinkedHashSet<ExternalReference> externalReferences = null;

    protected LinkedHashSet<MarkingDefinition> objectMarkingRefs = null;

    @JsonProperty("granular_markings")
    @JsonInclude(NON_NULL)
    protected LinkedHashSet<MarkingDefinition> granularMarkings = null;

    @JsonProperty("definition_type")
    protected String definitionType;

    protected MarkingObjectType definition;

    //
    // Getters and Setters
    //

    public String getSpecVersion() {
        return specVersion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        Objects.requireNonNull(id, "Id cannot be null");
        if (StringUtils.isBlank(getType())){
            throw new IllegalArgumentException("Cannot set id without Type property being defined");

        }else if (StringUtils.isNotBlank(id)){
            this.id = String.join("--", getType(), id);

        } else {
            throw new IllegalArgumentException("Id can't be null or blank");
        }
    }

    public String getCreatedByRef() {
        return createdByRef;
    }

    public void setCreatedByRef(String createdByRef) {
        this.createdByRef = createdByRef;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public LinkedHashSet<ExternalReference> getExternalReferences() {
        return externalReferences;
    }

    public void setExternalReferences(LinkedHashSet<ExternalReference> externalReferences) {
        this.externalReferences = externalReferences;
    }

    @JsonIgnore
    public LinkedHashSet<MarkingDefinition> getObjectMarkingRefs() {
        return objectMarkingRefs;
    }

    @JsonProperty("object_marking_refs")
    @JsonInclude(NON_NULL)
    public LinkedHashSet<String> getObjectMarkingRefsAsStrings() {
        if (this.getObjectMarkingRefs() != null) {
            return this.getObjectMarkingRefs().stream()
                    .map(MarkingDefinition::getId)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        } else {
            return null;
        }
    }

    public void setObjectMarkingRefs(LinkedHashSet<MarkingDefinition> objectMarkingRefs) {
        this.objectMarkingRefs = objectMarkingRefs;
    }

    public void setObjectMarkingRefs(MarkingDefinition... objectMarkingRefs) {
        this.setObjectMarkingRefs(new LinkedHashSet<>(Arrays.asList(objectMarkingRefs)));
    }

    public void addObjectMarkingRefs(MarkingDefinition... objectMarkingRefs) {
        if (this.getObjectMarkingRefs() == null){
            this.setObjectMarkingRefs(new LinkedHashSet<>(Arrays.asList(objectMarkingRefs)));
        } else {
            this.getObjectMarkingRefs().addAll(Arrays.asList(objectMarkingRefs));
        }
    }

    public LinkedHashSet<MarkingDefinition> getGranularMarkings() {
        return granularMarkings;
    }

    public void setGranularMarkings(LinkedHashSet<MarkingDefinition> granularMarkings) {
        this.granularMarkings = granularMarkings;
    }

    public String getDefinitionType() {
        return definitionType;
    }

    public void setDefinitionType(String definitionType) {
        this.definitionType = definitionType;
    }

    public MarkingObjectType getDefinition() {
        return definition;
    }

    public void setDefinition(MarkingObjectType definition) {
        this.definition = definition;
    }
}
