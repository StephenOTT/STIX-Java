package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.IndicatorProperties;
import io.digitalstate.stix.vocabularies.Vocabularies;
import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class Indicator extends IndicatorProperties implements StixDomainObject {

    private static final String TYPE = "indicator";

    public Indicator(String pattern,
                     ZonedDateTime validFrom,
                     LinkedHashSet<String> indicatorLabels){

        setType(TYPE);
        setId(generateUuidAsString());
        setPattern(pattern);
        setValidFrom(validFrom);
        setLabels(indicatorLabels);
    }

    public Indicator(String pattern, ZonedDateTime validFrom, String... indicatorLabels){
        this(pattern,validFrom, new LinkedHashSet<>(Arrays.asList(indicatorLabels)));
    }

    @Override
    public void setName(String name) {
        if (StringUtils.isNotEmpty(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be blank (but it can be null)");
        }
    }

    @Override
    public void setPattern(String pattern) {
        if (StringUtils.isNotBlank(pattern)){
            this.pattern = pattern;
        } else {
            throw new IllegalArgumentException("Pattern cannot be null or blank");
        }
    }

    @Override
    public void setValidFrom(ZonedDateTime validFrom) {
        if (validFrom != null){
            this.validFrom = validFrom;
        } else {
            throw new IllegalArgumentException("ValidFrom cannot be null");
        }
    }

    /**
     *
     * @param labels labels are enforced with indicatorLabels vocabulary
     */
    @Override
    public void setLabels(LinkedHashSet<String> labels) {
        Set<String> indicatorLabels = Vocabularies.getIndicatorLabels();

        if (!labels.isEmpty() && labels.containsAll(indicatorLabels)){
            super.setLabels(labels);
        } else {
            throw new IllegalArgumentException("At least one label must be provided");
        }
    }
}
