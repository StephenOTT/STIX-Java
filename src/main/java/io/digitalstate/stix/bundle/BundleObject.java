package io.digitalstate.stix.bundle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.json.StixParsers;
import io.digitalstate.stix.validation.GenericValidation;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "bundle", groups = {DefaultValuesProcessor.class})
@JsonTypeName("bundle")
@Value.Style(typeImmutable = "Bundle", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = Bundle.class) @JsonDeserialize(builder = Bundle.Builder.class)
@JsonPropertyOrder({"type", "id", "spec_version", "objects"})
public interface BundleObject extends GenericValidation, Serializable {

    @NotBlank
    @JsonProperty("type")
    String getType();

    @NotBlank
    @JsonProperty("id")
    String getId();

    @NotBlank
    @JsonProperty("spec_version")
    @Value.Default
    default String getSpecVersion() {
        return StixSpecVersion.SPECVERSION;
    }

    @Size(min = 1, message = "Must have at least 1 object in bundle")
    @JsonProperty(value = "objects", access = JsonProperty.Access.WRITE_ONLY)
    Set<BundleableObject> getObjects();

    @JsonIgnore
    @Value.Lazy
    default String toJsonString() {
        JsonNode response = StixParsers.getJsonMapper(true).valueToTree(this);
        ObjectNode responseNode = (ObjectNode) response;
        responseNode.putArray("objects");
        ArrayNode objects = (ArrayNode) response.get("objects");

        getObjects().forEach(o -> {
            String redactedJson = o.toJsonString();
            try {
                //@TODO refactor so the double JSON parsing does not need to happen
                JsonNode redactedJsonNode = StixParsers.getJsonMapper(true).readTree(redactedJson);
                ObjectNode redactedObjectNode = (ObjectNode) redactedJsonNode;
                if (!redactedObjectNode.isNull() && redactedObjectNode.size() > 0) {
                    objects.add(redactedObjectNode);
                }
            } catch (IOException e) {
                throw new IllegalStateException("Failed to Parse Object within bundle JSON");
            }
        });

        try {
            return StixParsers.getJsonMapper(true).writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to Parse Bundle JSON");
        }

    }
}
