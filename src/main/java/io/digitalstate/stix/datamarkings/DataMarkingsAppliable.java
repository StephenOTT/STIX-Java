package io.digitalstate.stix.datamarkings;

import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
import io.digitalstate.stix.datamarkings.granular.GranularMarking;

import java.util.LinkedHashSet;

public interface DataMarkingsAppliable {
    LinkedHashSet<MarkingDefinition> getObjectMarkingRefs();
    void setObjectMarkingRefs(LinkedHashSet<MarkingDefinition> objectMarkingRefs);

    LinkedHashSet<GranularMarking> getGranularMarkings();
    void setGranularMarkings(LinkedHashSet<GranularMarking> granularMarkings);
}
