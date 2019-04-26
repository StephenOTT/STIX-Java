package io.digitalstate.stix.coo.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.extension.CyberObservableExtension;
import io.digitalstate.stix.coo.objects.ProcessCoo;
import io.digitalstate.stix.validation.contraints.businessrule.BusinessRule;
import io.digitalstate.stix.validation.contraints.coo.allowedparents.AllowedParents;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The Windows Process extension specifies a default extension for capturing properties specific to Windows processes.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "windows-process-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, passAnnotations = {AllowedParents.class}, depluralize = true)
@JsonSerialize(as = WindowsProcessExtension.class) @JsonDeserialize(builder = WindowsProcessExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "aslr_enabled", "dep_enabled", "priority", "owner_sid", "window_title", "startup_info" })
@JsonTypeName("windows-process-ext")
@AllowedParents({ProcessCoo.class})
@BusinessRule(ifExp = "isAslrEnabled().orElse(false) == true || isDepEnabled().orElse(false) == true", thenExp = "isDepEnabled().orElse(false) == false || isAslrEnabled().orElse(false) == false", errorMessage = "Dep and ASLR cannot both be enabled")
public interface WindowsProcessExtensionExt extends CyberObservableExtension {

    //@TODO Add business rule for having at least 1 property

    @JsonProperty("aslr_enabled")
    @JsonPropertyDescription("Specifies whether Address Space Layout Randomization (ASLR) is enabled for the process.")
    @NotNull
    Optional<Boolean> isAslrEnabled();

    @JsonProperty("dep_enabled")
    @JsonPropertyDescription("Specifies whether Data Execution Prevention (DEP) is enabled for the process.")
    @NotNull
    Optional<Boolean> isDepEnabled();

    @JsonProperty("priority")
    @JsonPropertyDescription("Specifies the current priority class of the process in Windows.")
    Optional<String> getPriority();

    @JsonProperty("owner_sid")
    @JsonPropertyDescription("Specifies the Security ID (SID) value of the owner of the process.")
    Optional<String> getOwnerSid();

    @JsonProperty("window_title")
    @JsonPropertyDescription("Specifies the title of the main window of the process.")
    Optional<String> getWindowTitle();

    @JsonProperty("startup_info")
    @JsonPropertyDescription("Specifies the STARTUP_INFO struct used by the process, as a dictionary.")
    Map<String,String> getStartupInfo();

}
