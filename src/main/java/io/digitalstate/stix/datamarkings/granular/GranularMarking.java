package io.digitalstate.stix.datamarkings.granular;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class GranularMarking {

    protected MarkingDefinition markingRef;

    //@TODO Convert selectors from String into its own object that validates proper Selector formats
    protected LinkedHashSet<String> selectors;

    public GranularMarking(MarkingDefinition markingDefinition, LinkedHashSet<String> selectors){
        setMarkingRef(markingDefinition);
        setSelectors(selectors);
    }

    public GranularMarking(MarkingDefinition markingDefinition, String... selectors){
        this(markingDefinition, new LinkedHashSet<>(Arrays.asList(selectors)));
    }

    //
    // Getters and Setters
    //

    @JsonIgnore
    public MarkingDefinition getMarkingRef() {
        return markingRef;
    }

    public void setMarkingRef(MarkingDefinition markingRef) {
        this.markingRef = markingRef;
    }

    @JsonProperty("marking_ref")
    public String getMarkingRefId(){
        return this.getMarkingRef().getId();
    }

    public LinkedHashSet<String> getSelectors() {
        return selectors;
    }

    public void setSelectors(LinkedHashSet<String> selectors) {
        this.selectors = selectors;
    }
}
