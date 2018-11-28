package io.digitalstate.stix.domainobjects.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.types.Hashes;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonDeserialize(using = ExternalReference.Deserializer.class)
@JsonPropertyOrder({"source_name", "description", "url", "hashes", "external_id"})
public class ExternalReference {

    @JsonProperty("source_name")
    String sourceName;

    @JsonInclude(NON_NULL)
    String description = null;

    @JsonInclude(NON_NULL)
    String url = null;

    @JsonInclude(NON_EMPTY)
    LinkedHashSet<Hashes> hashes = new LinkedHashSet<>();

    @JsonProperty("external_id")
    @JsonInclude(NON_NULL)
    String externalId = null;

    public ExternalReference(String sourceName){
        setSourceName(sourceName);
    }

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


    /**
     * Used for JSON Deserialization
     */
    private ExternalReference(){}

    public static class Deserializer extends StdDeserializer<ExternalReference> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public ExternalReference deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            ExternalReference object = new ExternalReference();

            Optional<JsonNode> source_name = Optional.ofNullable(node.get("source_name"));
            source_name.ifPresent(o->{
                if (!o.isNull()){
                    object.setSourceName(o.asText());
                } else {
                    throw new IllegalArgumentException("source_name is required");
                }
            });
            source_name.orElseThrow(() -> new IllegalArgumentException("source_name is required"));

            Optional<JsonNode> description = Optional.ofNullable(node.get("description"));
            description.ifPresent(o->{
                if (!o.isNull()){
                    object.setDescription(o.asText());
                }
            });

            Optional<JsonNode> url = Optional.ofNullable(node.get("url"));
            url.ifPresent(o->{
                if (!o.isNull()){
                    object.setUrl(o.asText());
                }
            });

            Optional<JsonNode> hashes = Optional.ofNullable(node.get("hashes"));
            hashes.ifPresent(o->{
                if (!o.isArray()){
                    //@TODO
//                    object.setHashes(o.asText());
                } else {
                    throw new IllegalArgumentException("hashes must be a array");
                }
            });

            Optional<JsonNode> external_id = Optional.ofNullable(node.get("external_id"));
            external_id.ifPresent(o->{
                if (!o.isNull()){
                    object.setExternalId(o.asText());
                }
            });

            return object;
        }
    }

}
