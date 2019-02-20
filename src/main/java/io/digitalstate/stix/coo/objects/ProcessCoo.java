package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.validation.contraints.businessrule.BusinessRule;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * process
 * <p>
 * The Process Object represents common properties of an instance of a computer
 * program as executed on an operating system.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "process", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonSerialize(as = Process.class) @JsonDeserialize(builder = Process.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonTypeName("process")
@JsonPropertyOrder({ "type", "extensions", "is_hidden", "pid", "name", "created", "cwd", "arguments", "command_line",
        "environment_variables", "opened_connection_refs", "creator_user_ref", "binary_ref", "parent_ref",
        "child_refs" })
@BusinessRule(ifExp = "true", thenExp = "getExtensions().isEmpty() == false || isHidden().isPresent() == true || getPid().isPresent() == true || getName().isPresent() == true || getCreated().isPresent() == true || getCwd().isPresent() == true || getArguments().isEmpty() == false || getCommandLine().isPresent() == true || getEnvironmentVariables().isEmpty() == false || getOpenedConnectionRefs().isEmpty() == false || getCreatorUserRef().isPresent() == true || getBinaryRef().isPresent() == true || getParentRef().isPresent() == true || getChildRefs().isEmpty() == false", errorMessage = "A Process Object MUST contain at least one property (other than type) from this object (or one of its extensions).")
public interface ProcessCoo extends CyberObservableObject {

    @JsonProperty("is_hidden")
    @JsonPropertyDescription("Specifies whether the process is hidden.")
    @NotNull
    Optional<Boolean> isHidden();

    @JsonProperty("pid")
    @JsonPropertyDescription("Specifies the Process ID, or PID, of the process.")
    Optional<Long> getPid();

    @JsonProperty("name")
    @JsonPropertyDescription("Specifies the name of the process.")
    Optional<String> getName();

    @JsonProperty("created")
    @JsonPropertyDescription("Specifies the date/time at which the process was created.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    Optional<Instant> getCreated();

    @JsonProperty("cwd")
    @JsonPropertyDescription("Specifies the current working directory of the process.")
    Optional<String> getCwd();

    //@TODO need better clarification in the STIX SPEC about if this should be a SET or LIST. Are duplicate arguments allowed?
    @JsonProperty("arguments")
    @JsonPropertyDescription("Specifies the list of arguments used in executing the process.")
    List<String> getArguments();

    @JsonProperty("command_line")
    @JsonPropertyDescription("Specifies the full command line used in executing the process, including the process name (depending on the operating system).")
    Optional<String> getCommandLine();

    @JsonProperty("environment_variables")
    @JsonPropertyDescription("Specifies the list of environment variables associated with the process as a dictionary.")
    Map<String,String> getEnvironmentVariables();

    @JsonProperty("opened_connection_refs")
    @JsonPropertyDescription("Specifies the list of network connections opened by the process, as a reference to one or more Network Traffic Objects.")
    Set<String> getOpenedConnectionRefs();

    @JsonProperty("creator_user_ref")
    @JsonPropertyDescription("Specifies the user that created the process, as a reference to a User Account Object.")
    Optional<String> getCreatorUserRef();

    @JsonProperty("binary_ref")
    @JsonPropertyDescription("Specifies the executable binary that was executed as the process, as a reference to a File Object.")
    Optional<String> getBinaryRef();

    @JsonProperty("parent_ref")
    @JsonPropertyDescription("Specifies the other process that spawned (i.e. is the parent of) this one, as represented by a Process Object.")
    Optional<String> getParentRef();

    @JsonProperty("child_refs")
    @JsonPropertyDescription("Specifies the other processes that were spawned by (i.e. children of) this process, as a reference to one or more other Process Objects.")
    Set<String> getChildRefs();

}
