package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
/**
 * mutex
 * <p>
 * The Mutex Object represents the properties of a mutual exclusion (mutex) object.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "mutex", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonTypeName("mutex")
@JsonSerialize(as = Mutex.class) @JsonDeserialize(builder = Mutex.Builder.class)
@JsonPropertyOrder({"type", "extensions", "name"})
public interface MutexCoo extends CyberObservableObject {

	@JsonProperty("name")
	@JsonPropertyDescription("Specifies the name of the mutex object.")
	@NotNull
	String getName();
}
