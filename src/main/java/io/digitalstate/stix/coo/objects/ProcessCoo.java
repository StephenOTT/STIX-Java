package io.digitalstate.stix.coo.objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.immutables.serial.Serial;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;

/**
 * process
 * <p>
 * The Process Object represents common properties of an instance of a computer
 * program as executed on an operating system.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "process", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = Process.class) @JsonDeserialize(builder = Process.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonTypeName("process")
@JsonPropertyOrder({ "type", "extensions", "is_hidden", "pid", "name", "created", "cwd", "arguments", "command_line",
		"environment_variables", "opened_connection_refs", "creator_user_ref", "binary_ref", "parent_ref",
		"child_refs" })
public interface ProcessCoo extends CyberObservableObject {
	
	@JsonProperty("is_hidden")
	@JsonPropertyDescription("Specifies whether the process is hidden.")
	Optional<Boolean> getHidden();

	@JsonProperty("pid")
	@JsonPropertyDescription("Specifies the Process ID, or PID, of the process.")
	Optional<Integer> getPid();

	@JsonProperty("name")
	@JsonPropertyDescription("Specifies the name of the process.")
	Optional<String> getName();

	@JsonProperty("created")
	@JsonPropertyDescription("Specifies the date/time at which the process was created.")
	Optional<Instant> getCreated();

	@JsonProperty("cwd")
	@JsonPropertyDescription("Specifies the current working directory of the process.")
	Optional<String> getCwd();

	@JsonProperty("arguments")
	@JsonPropertyDescription("Specifies the list of arguments used in executing the process.")
	Set<String> getArguments();

	@JsonProperty("command_line")
	@JsonPropertyDescription("Specifies the full command line used in executing the process, including the process name (depending on the operating system).")
	Optional<String> getCommandLine();

	@JsonProperty("environment_variables")
	@JsonPropertyDescription("Specifies the list of environment variables associated with the process as a dictionary.")
	Map<String,String> getEnvironmentVariables();

	@JsonProperty("opened_connection_refs")
	@JsonPropertyDescription("Specifies the list of network connections opened by the process, as a reference to one or more Network Traffic Objects.")
	Set<String> getGpenedConnectionRefs();

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
