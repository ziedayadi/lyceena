package com.zka.lyceena.dto;

import com.zka.lyceena.entities.ref.UserStatus;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String emailAdress;
    private UserStatus status;
    private String userName;
    private List<String> roles;

}
