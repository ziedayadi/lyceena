package com.zka.lyceena.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

@Component
public class UserDetailsProviderImpl implements UserDetailsProvider {


   // private final static String SIGNING_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4UuAlxFFNNBMbpf/ICb2Jo8LuOu+E3BK5TPtO0f3D8L+xke7wu8PhrGHdZhl5zh4A6V1bS8FcPbivPzaFxZj1eKozVX1O+7Z/5vaL/I2c+DWxY+YmjEeZn8DIozjLEA4VjGXE1mwMX4Y3r2CjqlTg0uChDHeVPuR5yfP5nWEd7JVYmhwFbpz/ZWjfe0froyn+had/uLgj5rXSWE2lcIBeIv7yXLvMOFEBugXvCR/Hwzo+mzbXf73hLibndi3K8k9qQk6v/3boYBYAttvUMak26SuqZCHEjlo/OEGvjuRfockdjRigU2qIMF6Rpx9AQLKWWwKZws8KqgZK+RS3YguRwIDAQAB";

    @Value( "${jwt.signingkey}" )
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

        userDetails.setRoles(roles);
        userDetails.setFirstName(firstName);
        userDetails.setLastName(lastName);
        userDetails.setKeycloakUserId(keycloakUserId);
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
