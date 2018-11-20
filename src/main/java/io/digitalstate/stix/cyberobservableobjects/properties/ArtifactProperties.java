package io.digitalstate.stix.cyberobservableobjects.properties;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.digitalstate.stix.types.Hashes;

import java.util.LinkedHashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class ArtifactProperties extends CyberObservableObjectCommonProperties {

    @JsonInclude(NON_NULL)
    protected String mimeType;

    @JsonInclude(NON_NULL)
    protected Byte[] payloadBin;

    @JsonInclude(NON_NULL)
    protected String url;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<Hashes> hashes;


    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Byte[] getPayloadBin() {
        return payloadBin;
    }

    public void setPayloadBin(Byte[] payloadBin) {
        this.payloadBin = payloadBin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LinkedHashSet<Hashes> getHashes() {
        return hashes;
    }

    public void setHashes(LinkedHashSet<Hashes> hashes) {
        this.hashes = hashes;
    }
}
