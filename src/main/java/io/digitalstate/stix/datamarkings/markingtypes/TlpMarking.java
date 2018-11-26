package io.digitalstate.stix.datamarkings.markingtypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.digitalstate.stix.vocabularies.TlpLevels;

import java.util.Objects;

@JsonPropertyOrder({"tlp"})
public class TlpMarking extends MarkingObjectTypeCommonProperties implements MarkingObjectType {

    private static final String type = "tlp";

    private String tlp;

    private TlpLevels tlpLevelsVocab = new TlpLevels();

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

    @JsonIgnore
    public TlpLevels getTlpLevelsVocab() {
        return tlpLevelsVocab;
    }

    @JsonIgnore
    public void setTlpLevelsVocab(TlpLevels tlpLevelsVocab) {
        Objects.requireNonNull(tlpLevelsVocab, "tlpLevelsVocab cannot be null");
        this.tlpLevelsVocab = tlpLevelsVocab;
    }

    public void setTlp(String tlp) {
        if (tlpLevelsVocab.vocabularyContains(tlp)) {
            this.tlp = tlp;
        } else {
            throw new IllegalArgumentException("TLP value (" + tlp + ")" + " is not a valid TLP level");
        }
    }
}
