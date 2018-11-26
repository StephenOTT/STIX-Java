package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.vocabularies.IdentityClasses;
import io.digitalstate.stix.vocabularies.IndustrySectors;
import io.digitalstate.stix.vocabularies.StixVocabulary;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "description",
        "identity_class", "sectors", "contact_information"})
public abstract class IdentityProperties extends CommonProperties{
    private String name;

    @JsonInclude(NON_NULL)
    private String description = null;

    @JsonProperty("identity_class")
    private String identityClass;

    @JsonInclude(NON_NULL)
    private LinkedHashSet<String> sectors = null;

    @JsonInclude(NON_NULL)
    @JsonProperty("contact_information")
    private String contactInformation = null;

    @JsonIgnore
    private StixVocabulary identityClassesVocab = new IdentityClasses();

    @JsonIgnore
    private StixVocabulary industrySectorsVocab = new IndustrySectors();


    //
    // Getters and Setters
    //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isNotBlank(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can't be null or blank");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentityClass() {
        return identityClass;
    }

    public void setIdentityClass(String identityClass) {
        Objects.requireNonNull(identityClass, "identityClass cannot be null");

        if (getIdentityClassesVocab().vocabularyContains(identityClass)){
            this.identityClass = identityClass;
        } else {
            throw new IllegalArgumentException(identityClass + " is not a valid identity class");
        }
    }

    public LinkedHashSet<String> getSectors() {
        return sectors;
    }

    public void setSectors(LinkedHashSet<String> sectors) {
        if (getIndustrySectorsVocab().vocabularyContains(sectors)){
            this.sectors = sectors;
        } else {
            throw new IllegalArgumentException("One or more sectors are not valid industry sectors");
        }
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * Get the Identity Classes Vocabulary
     * @return
     */
    @JsonIgnore
    public StixVocabulary getIdentityClassesVocab() {
        return identityClassesVocab;
    }

    /**
     * Used to override the identity classes vocabulary.  Typically used for customizations of STIX.
     * @param identityClassesVocab
     */
    @JsonIgnore
    public void setIdentityClassesVocab(StixVocabulary identityClassesVocab) {
        Objects.requireNonNull(identityClassesVocab, "identityClassesVocab required" );
        this.identityClassesVocab = identityClassesVocab;
    }

    /**
     * Get Industry Sectors Vocabulary
     * @return
     */
    @JsonIgnore
    public StixVocabulary getIndustrySectorsVocab() {
        return industrySectorsVocab;
    }

    /**
     * Used to override the industry sectors vocabulary.  Typically used for customizations of STIX.
     * @param industrySectorsVocab
     */
    @JsonIgnore
    public void setIndustrySectorsVocab(StixVocabulary industrySectorsVocab) {
        Objects.requireNonNull(industrySectorsVocab, "industrySectorsVocab required" );
        this.industrySectorsVocab = industrySectorsVocab;
    }

    //
    // Helpers
    //

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();
        return bundleObjects;
    }
}
