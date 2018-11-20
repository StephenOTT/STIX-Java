package io.digitalstate.stix.helpers;

import java.util.UUID;

public class IdGeneration {
    public static String generateUuidAsString(){
        return UUID.randomUUID().toString();
    }
}
