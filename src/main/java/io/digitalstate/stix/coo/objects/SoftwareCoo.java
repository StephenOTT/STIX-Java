package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * software
 * <p>
 * The Software Object represents high-level properties associated with software, including software products.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "software", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonSerialize(as = Software.class) @JsonDeserialize(builder = Software.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonTypeName("software")
@JsonPropertyOrder({
    "type", "extensions", "cpe", "languages", "vendor", "version" })
public interface SoftwareCoo extends CyberObservableObject {

    @JsonProperty("name")
    @JsonPropertyDescription("Specifies the name of the software.")
    @NotNull
    String getName();
    
    /**
     *  TODO The value for this property MUST be a CPE v2.3 entry from the official NVD CPE Dictionary.
     *  regex pattern = "^cpe:2\\.3:[aho](?::(?:[a-zA-Z0-9!\"#$%&'()*+,\\\\-_.\/();<=>?@\\[\\]^`{|}~]|\\:)+){10}$"
     *  Is not valid for the @Pattern annotation (invalid escape chars)
     *  Remove @Pattern(regexp="^cpe:2\\.3:[aho]") until working solution is provided
     */
    @JsonProperty("cpe")
    @JsonPropertyDescription("Specifies the Common Platform Enumeration (CPE) entry for the software, if available.")
    Optional<String> getCpe();
    
    /**
     * TODO The value of each list member MUST be an ISO 639-2 language code.
     */
    @JsonProperty("languages")
    @JsonPropertyDescription("Specifies the languages supported by the software.")
    @Pattern(regexp="^[a-z]{3}$")
    Set<String> getLanguages();

    @JsonProperty("vendor")
    @JsonPropertyDescription("Specifies the name of the vendor of the software.")
    Optional<String> getVendor();

    @JsonProperty("version")
    @JsonPropertyDescription("Specifies the version of the software.")
    Optional<String> getVersion();

}
