package io.digitalstate.stix.custom.objects;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.bundle.BundleableObject;
import io.digitalstate.stix.custom.StixCustomObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;


@Value.Immutable @Serial.Version(1L)
//@DefaultTypeValue(value = "", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="Generic*", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonSerialize(as = CustomObject.class) @JsonDeserialize(builder = CustomObject.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings"})
public interface GenericCustomObject extends StixCustomObject {


}
