package io.digitalstate.stix.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.validation.constraints.Size;
import java.util.Map;

@Value.Immutable
@Value.Style(typeAbstract="*Type", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE)
@JsonSerialize(as = Hashes.class) @JsonDeserialize(builder = Hashes.Builder.class)
public interface HashesType {

    @Size(min = 1, message = "Must have at least 1 hash value")
    Map<String,String> hashes();

    //@TODO add annotations for restrictions
    /*
    he Hashes type represents 1 or more cryptographic hashes, as a special set of key/value pairs. Accordingly,
    the name of each hashing algorithm MUST be specified as a key in the dictionary and MUST identify the name of
    the hashing algorithm used to generate the corresponding value. This name SHOULD either be one of the values
    defined in the hash-algorithm-ov OR a custom value prepended with “x_” (e.g., “x_custom_hash”).

    Keys MUST be unique in each hashes property, MUST be in ASCII, and are limited to the characters a-z
    (lowercase ASCII), A-Z (uppercase ASCII), numerals 0-9, hyphen (-), and underscore (_). Keys SHOULD be no
    longer than 30 ASCII characters in length, MUST have a minimum length of 3 ASCII characters, MUST be no longer
    than 256 ASCII characters in length.
     */
}
