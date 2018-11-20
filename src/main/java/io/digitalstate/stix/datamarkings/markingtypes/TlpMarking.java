package io.digitalstate.stix.datamarkings.markingtypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.digitalstate.stix.vocabularies.Vocabularies;

import java.util.Objects;
import java.util.Set;

public class TlpMarking extends MarkingObjectTypeCommonProperties implements MarkingObjectType {

    private static final String type = "tlp";

    String tlp;

    public TlpMarking(String tlpValue){
        setTlp(tlpValue);
    }

    //
    // Getters and Setters
    //

    @Override
    @JsonIgnore
    public String getType() {
        return type;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        Objects.requireNonNull(tlp, "tlp cannot be null");

        Set<String> tlpLevels = Vocabularies.getTlpLevels();

        if (tlpLevels.contains(tlp)) {
            this.tlp = tlp;
        } else {
            throw new IllegalArgumentException("TLP value provided: ["+ tlp +"] is not a valid TLP.  Valid TLP values: " + tlpLevels.toString());
        }
    }
}
