package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.IndicatorProperties;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class Indicator extends IndicatorProperties implements StixDomainObject {

    private static final String TYPE = "indicator";

    public Indicator(String pattern,
                     ZonedDateTime validFrom,
                     LinkedHashSet<String> indicatorLabels){

        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setPattern(pattern);
        setValidFrom(validFrom);
        setLabels(indicatorLabels);
    }

    public Indicator(String pattern, ZonedDateTime validFrom, String... indicatorLabels){
        this(pattern,validFrom, new LinkedHashSet<>(Arrays.asList(indicatorLabels)));
    }
}
