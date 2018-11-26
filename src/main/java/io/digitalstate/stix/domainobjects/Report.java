package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.ReportProperties;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class Report extends ReportProperties implements StixDomainObject {

    private static final String TYPE = "report";

    public Report(String name,
                  LinkedHashSet<String> reportLabels,
                  ZonedDateTime publishedDateTime,
                  LinkedHashSet<String> objects){

        setType(TYPE);
        setId(TYPE, generateUuidAsString());
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
}
