package io.digitalstate.stix.bundle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.helpers.StixDataFormats;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "spec_version", "objects"})
public abstract class BundleProperties {
    protected String type;
    protected String id;

    @JsonProperty("spec_version")
    protected String specVersion;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<BundleObject> objects = null;


    //
    // Getters and Setters
    //

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        Objects.requireNonNull(id, "Id cannot be null");

        if (StringUtils.isBlank(getType())){
            throw new IllegalArgumentException("Cannot set id without Type property being defined");

        } else if (StringUtils.isNotBlank(id)){
            this.id = String.join("--", getType(), id);

        } else {
            throw new IllegalArgumentException("Id can't be null or blank");
        }
    }

    public String getSpecVersion() {
        return specVersion;
    }
    public void setSpecVersion(String specVersion) {
        this.specVersion = specVersion;
    }

    public LinkedHashSet<BundleObject> getObjects() {
        objects.removeIf(Objects::isNull);
        return objects;
    }

    public void setObjects(LinkedHashSet<BundleObject> objects) {
        this.objects = objects;
    }

    public void setObjects(BundleObject... objects) {
        setObjects(new LinkedHashSet<>(Arrays.asList(objects)));
    }

    public void addObjects(BundleObject... bundleObjects) {
        if (this.getObjects() == null){
            this.setObjects(new LinkedHashSet<>(Arrays.asList(bundleObjects)));
        } else {
            this.getObjects().addAll(Arrays.asList(bundleObjects));
        }
    }

    @Override
    public String toString() {
        System.out.println(getObjects().size());
        return new ReflectionToStringBuilder(this, new RecursiveToStringStyle()).toString();
    }

    /**
     * Deep Converts the STIX Bundle and inner STIX Objects into a JSON string as per STIX JSON Spec.
     * @return String JSON string of STIX Bundle and inner STIX Objects.
     * @throws JsonProcessingException
     */
    public String toJsonString() throws JsonProcessingException {
        return StixDataFormats.getJsonMapper().writeValueAsString(this);
    }

}
