package io.digitalstate.stix.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Holds contract for conversation into Json and Json Strings
 */
public interface JsonConvertable {

    String toJsonString() throws JsonProcessingException;

}
