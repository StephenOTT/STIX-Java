package io.digitalstate.stix.datamarkings.object;

import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;

public class ObjectMarking {

    protected String markingRef;

    public ObjectMarking(String markingRef){
        setMarkingRef(markingRef);
    }
    public ObjectMarking(MarkingDefinition markingDefinition){
        setMarkingRef(markingDefinition);
    }

    //
    // Getters and Setters
    //

    public String getMarkingRef() {
        return this.markingRef;
    }

    public void setMarkingRef(String markingRef) {
        this.markingRef = markingRef;
    }

    public void setMarkingRef(MarkingDefinition markingDefinition){
        this.setMarkingRef(markingDefinition.getId());
    }
}
