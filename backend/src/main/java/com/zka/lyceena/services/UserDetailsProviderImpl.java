package com.zka.lyceena.services;

import com.zka.lyceena.security.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

public class UserDetailsProviderImpl implements UserDetailsProvider {


    public UserDetailsProviderImpl(String signingKey) {
        this.signingKey = signingKey;
    }

    private String signingKey;

    @Override
    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        String tokenValue = jwt.getTokenValue();

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(tokenValue);
        UserDetails userDetails = new UserDetails();
        List<String> roles = (List<String>)  claimsJws.getBody().get("realm_access",Map.class).get("roles");
        String firstName = claimsJws.getBody().get("given_name",String.class);
        String lastName = claimsJws.getBody().get("family_name",String.class);
        String keycloakUserId = claimsJws.getBody().get("sub",String.class);
        String userName = claimsJws.getBody().get("preferred_username",String.class);

        userDetails.setRoles(roles);
        userDetails.setFirstName(firstName);
        userDetails.setLastName(lastName);
        userDetails.setKeycloakUserId(keycloakUserId);
        userDetails.setUserName(userName);

        return userDetails;
    }

    private PublicKey getKey(){
        try{
            byte[] byteKey = Base64.getDecoder().decode(signingKey);
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePublic(X509publicKey);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
