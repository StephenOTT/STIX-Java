package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.relationshipobjects.Relation;
import io.digitalstate.stix.vocabularies.ReportLabels;
import io.digitalstate.stix.vocabularies.StixVocabulary;
import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "description",
        "published", "object_refs", })
public abstract class ReportProperties extends CommonProperties {
    private String name;

    @JsonInclude(NON_NULL)
    private String description = null;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime published;

    private LinkedHashSet<Relation<BundleObject>> objectRefs = new LinkedHashSet<>();

    // Vocabulary Instances
    private StixVocabulary reportLabelsVocab = new ReportLabels();

    //
    // Getters and Setters
    //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isNotBlank(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can't be null or blank");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getPublished() {
        return published;
    }

    public void setPublished(ZonedDateTime published) {
        Objects.requireNonNull(published, "published cannot be null");
        this.published = published;
    }

    @JsonIgnore
    public LinkedHashSet<Relation<BundleObject>> getObjectRefs() {
        return objectRefs;
    }

    @JsonProperty("object_refs")
    public LinkedHashSet<String> getObjectRefsIds(){
        LinkedHashSet<String> ids = new LinkedHashSet<>();
        getObjectRefs().forEach(o->{
            if (o.hasObject()){
                ids.add(o.getObject().getId());
            } else {
                ids.add(o.getId());
            }
        });
        return ids;
    }

    public void setObjectRefs(LinkedHashSet<Relation<BundleObject>> objectRefs) {
        Objects.requireNonNull(objectRefs, "objectRefs cannot be null");
        if (objectRefs.isEmpty()){
            throw new NullPointerException("objectRefs cannot be empty");
        } else {
            this.objectRefs = objectRefs;
        }
    }


    @JsonIgnore
    public StixVocabulary getReportLabelsVocab() {
        return reportLabelsVocab;
    }

    @JsonIgnore
    public void setReportLabelsVocab(StixVocabulary reportLabelsVocab) {
        Objects.requireNonNull(reportLabelsVocab, "reportLabelsVocab cannot be null");
        this.reportLabelsVocab = reportLabelsVocab;
    }


    /**
     * Labels describing the primary subject of the report (report-label-ov).
     * @param labels
     */
    @Override
    public void setLabels(LinkedHashSet<String> labels) {
        Objects.requireNonNull(labels, "labels cannot be null");

        if (getReportLabelsVocab().vocabularyContains(labels)){
            super.setLabels(labels);
        } else {
            throw new IllegalArgumentException("One or more labels are not valid Report labels");
        }
    }


    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        getObjectRefs().forEach(relation->{
            if (relation.hasObject()){
                bundleObjects.add(relation.getObject());
            }
        });

        return bundleObjects;
    }

    @JsonIgnore
    public void hydrateRelationsWithObjects(LinkedHashSet<BundleObject> bundleObjects){

    }
}
