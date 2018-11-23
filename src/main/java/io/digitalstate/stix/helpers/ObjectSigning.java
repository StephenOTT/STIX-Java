package io.digitalstate.stix.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;

public class ObjectSigning {

    private static Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String signObject(StixDomainObject object) throws JsonProcessingException {

        String jws = Jwts.builder()
                .setHeaderParam("signed_by", "www.someCTIorg.cti" )
                .setPayload(object.toJsonString())
                .signWith(KEY)
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
