package io.digitalstate.stix.cyberobservableobjects.properties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.digitalstate.stix.types.Hashes;

import java.util.LinkedHashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "dictionary", "mime_type", "payload_bin",
        "url", "hashes"})
public class ArtifactProperties extends CyberObservableObjectCommonProperties {

    @JsonProperty("mime_type")
    @JsonInclude(NON_NULL)
    protected String mimeType;

    @JsonProperty("payload_bin")
    @JsonInclude(NON_NULL)
    protected Byte[] payloadBin;

    @JsonInclude(NON_NULL)
    protected String url;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<Hashes> hashes;

    //
    // Getters and Setters
    //


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
