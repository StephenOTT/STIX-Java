package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.ReportProperties;
import io.digitalstate.stix.vocabularies.Vocabularies;
import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class Report extends ReportProperties implements StixDomainObject {

    private static final String TYPE = "report";

    public Report(String name,
                  LinkedHashSet<String> reportLabels,
                  ZonedDateTime publishedDateTime,
                  LinkedHashSet<String> objects){

        setType(TYPE);
        setId(generateUuidAsString());
        setName(name);
        setLabels(reportLabels);
        setPublished(publishedDateTime);
        setObjectRefs(objects);
    }

    public Report(String name,
                  LinkedHashSet<String> reportLabels,
                  ZonedDateTime publishedDateTime,
                  String... objects){

        this(name, reportLabels, publishedDateTime, new LinkedHashSet<>(Arrays.asList(objects)));
    }
    public Report(String name,
                  String[] reportLabels,
                  ZonedDateTime publishedDateTime,
                  String... objects){

        this(name, new LinkedHashSet<>(Arrays.asList(reportLabels)), publishedDateTime, new LinkedHashSet<>(Arrays.asList(objects)));
    }

    //
    // Getters and Setters
    //

    @Override
    public void setName(String name) {
        if (StringUtils.isNotBlank(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can't be null or blank");
        }
    }

    @Override
    public void setPublished(ZonedDateTime publishedDateTime) {
        if (publishedDateTime != null){
            this.published = publishedDateTime;
        } else {
            throw new IllegalArgumentException("publishedDateTime cannot be null");
        }
    }

    @Override
    public void setObjectRefs(LinkedHashSet<String> objectRefs) {
        if (!objectRefs.isEmpty()){
            this.objectRefs = objectRefs;
        } else {
            throw new IllegalArgumentException("At least one StixDomainObject object ref is required");
        }
    }

    /**
     *
     * @param labels labels are enforced with reportLabel vocabulary
     */
    @Override
    public void setLabels(LinkedHashSet<String> labels) {
        Set<String> reportLabels = Vocabularies.getReportLabel();

        if (!labels.isEmpty() && labels.containsAll(reportLabels)){
            this.labels = labels;
        } else {
            throw new IllegalArgumentException("At least one label must be provided");
        }
    }
}
