package io.digitalstate.stix.coo;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.digitalstate.stix.common.StixCustomProperties;
import io.digitalstate.stix.validation.contraints.coo.allowedparents.ValidateExtensions;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY)
@ValidateExtensions
public interface CyberObservableObject extends Serializable,
        CyberObservableObjectCommonProperties,
        StixCustomProperties {

}
