package io.digitalstate.stix.datamarkings;

import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
import io.digitalstate.stix.datamarkings.granular.GranularMarking;
import io.digitalstate.stix.relationshipobjects.Relation;

import java.util.LinkedHashSet;

public interface DataMarkingsAppliable {
    LinkedHashSet<Relation<MarkingDefinition>> getObjectMarkingRefs();
    void setObjectMarkingRefs(LinkedHashSet<Relation<MarkingDefinition>> objectMarkingRefs);

    LinkedHashSet<GranularMarking> getGranularMarkings();
    void setGranularMarkings(LinkedHashSet<GranularMarking> granularMarkings);
}
