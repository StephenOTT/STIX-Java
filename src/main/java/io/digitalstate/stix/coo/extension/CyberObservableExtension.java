package io.digitalstate.stix.coo.extension;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.digitalstate.stix.common.StixCustomProperties;
import io.digitalstate.stix.validation.GenericValidation;


/**
 * Interface to tag Cyber Observable Extension classes
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY)
public interface CyberObservableExtension extends
		CyberObservableExtensionCommonProperties,
		GenericValidation,
		StixCustomProperties {

}
