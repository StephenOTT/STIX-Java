package io.digitalstate.stix.datamarkings;

import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;

import java.util.LinkedHashSet;

public interface DataMarkingsAppliable {
    LinkedHashSet<MarkingDefinition> getObjectMarkingRefs();
    void setObjectMarkingRefs(LinkedHashSet<MarkingDefinition> objectMarkingRefs);

    LinkedHashSet<MarkingDefinition> getGranularMarkings();
    void setGranularMarkings(LinkedHashSet<MarkingDefinition> granularMarkings);
}
