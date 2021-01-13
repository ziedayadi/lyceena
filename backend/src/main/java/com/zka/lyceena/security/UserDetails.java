package com.zka.lyceena.security;

import lombok.Data;

import java.util.List;

@Data
public class UserDetails {

    private String keycloakUserId;
    private String firstName;
    private String lastName;
    private List<String> roles;

}
