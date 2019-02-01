package io.digitalstate.stix.coo.types;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.digitalstate.stix.coo.CyberExtension;
import io.digitalstate.stix.coo.extensions.NtfsAlternateDataStream;
import io.digitalstate.stix.coo.extensions.NtfsAlternateDataStream.Builder;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.hashingvocab.HashingVocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.HashingAlgorithms;

/**
 * The Alternate Data Stream type represents an NTFS alternate data stream.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "alternate-data-stream-type", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Obj", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = NtfsAlternateDataStream.class) @JsonDeserialize(builder = NtfsAlternateDataStream.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "name", "hashes", "size" })
@JsonTypeName("alternate-data-stream-type")
public interface NtfsAlternateDataStreamObj extends CyberExtension {

	@JsonProperty("name")
	@JsonPropertyDescription("Specifies the name of the alternate data stream.")
	@NotNull
	String getName();
	

	@JsonProperty("hashes")
	@JsonPropertyDescription("Specifies a dictionary of hashes for the data contained in the alternate data stream.")
    Map<@Length(min = 3, max = 256) @HashingVocab(HashingAlgorithms.class) String, String> getHashes();
	

	@JsonProperty("size")
	@JsonPropertyDescription("Specifies the size of the alternate data stream, in bytes, as a non-negative integer.")
	Optional<Integer> getSize();
	
}
