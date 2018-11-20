package io.digitalstate.stix.types;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Map;

public class Hashes {
   private  Map<String,String> hashes;

    public Map getMap() {
        return hashes;
    }
    public void setMap(Map<String,String> hashes) {
        this.hashes = hashes;
    }

    @Override
    public String toString(){
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(this);
        return builder.toString();
    }
}
