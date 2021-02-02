package com.zka.lyceena.controllers;

import com.zka.lyceena.dto.UserDto;
import com.zka.lyceena.security.UserDetails;
import com.zka.lyceena.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UsersService usersService;

    @GetMapping("/")
    public UserDto getCurrentUserDetails(){
        return usersService.findCurrentUser();
    }

}
