package io.digitalstate.stix.cyberobservableobjects.properties;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.HashMap;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public abstract class CyberObservableObjectCommonProperties {

    protected String type;

    @JsonInclude(NON_NULL)
    protected HashMap<String, String> dictionary = null;

    //
    // Getters and Setters
    //

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public String toString(){
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(this);
        return builder.toString();
    }
}
