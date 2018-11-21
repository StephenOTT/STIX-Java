package io.digitalstate.stix.domainobjects.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.digitalstate.stix.types.Hashes;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.LinkedHashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"source_name", "description", "url", "hashes", "external_id"})
public class ExternalReference {

    @JsonProperty("source_name")
    String sourceName;

    @JsonInclude(NON_NULL)
    String description = null;

    @JsonInclude(NON_NULL)
    String url = null;

    @JsonInclude(NON_NULL)
    LinkedHashSet<Hashes> hashes = null;

    @JsonProperty("external_id")
    @JsonInclude(NON_NULL)
    String externalId = null;

    //
    // Getters and Setters
    //

    public String getSourceName() {
        return sourceName;
    }
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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

    public String getExternalId() {
        return externalId;
    }
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public String toString(){
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(this);
        return builder.toString();
    }
}
