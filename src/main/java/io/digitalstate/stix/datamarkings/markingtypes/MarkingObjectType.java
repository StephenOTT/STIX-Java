package io.digitalstate.stix.datamarkings.markingtypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.helpers.JsonConvertable;

public interface MarkingObjectType extends JsonConvertable {

    String getType();

}
