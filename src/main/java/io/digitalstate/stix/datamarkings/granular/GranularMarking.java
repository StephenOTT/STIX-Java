package io.digitalstate.stix.datamarkings.granular;

import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;

import java.util.LinkedHashSet;

public class GranularMarking {

    protected String markingRef;
    protected LinkedHashSet<String> selectors;

    public String getMarkingRef() {
        return markingRef;
    }

    public void setMarkingRef(String markingRef) {
        this.markingRef = markingRef;
    }

    public void setMarkingRef(MarkingDefinition markingDefinition){
        this.setMarkingRef(markingDefinition.getId());
    }

    public LinkedHashSet<String> getSelectors() {
        return selectors;
    }

    public void setSelectors(LinkedHashSet<String> selectors) {
        this.selectors = selectors;
    }
}
