package io.digitalstate.stix.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;

public class ObjectSigning {

    private static Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String signObject(StixDomainObject object) throws JsonProcessingException {

        String jws = Jwts.builder()
                .setHeaderParam("signed_by", "identity--d442813b-7e72-49a6-937a-3e351e219a18" )
                .setPayload(object.toJsonString())
                .signWith(KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        System.out.println(Base64.getEncoder().encodeToString(KEY.getEncoded()));
        return jws;

    }

    public static void validateObject(String objectJson) throws JwtException{
        try {
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(objectJson);
        } catch (JwtException e){
            System.out.println("VALIDATION OF SIGNATURE FAILED! " + e.getLocalizedMessage());
        }
    }

}
