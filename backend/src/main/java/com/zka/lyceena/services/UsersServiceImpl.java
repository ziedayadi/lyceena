package com.zka.lyceena.services;

import com.zka.lyceena.constants.Roles;
import com.zka.lyceena.dto.UserDto;
import com.zka.lyceena.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UserDetailsProvider userDetailsProvider;

    @Override
    public UserDto findCurrentUser() {
        UserDetails userDetails = this.userDetailsProvider.getCurrentUserDetails();
        UserDto userDto = new UserDto();
        userDto.setFirstName(userDetails.getFirstName());
        userDto.setLastName(userDetails.getLastName());
        userDto.setUserName(userDetails.getUserName());
        List<String> roles = userDetails.getRoles().stream().map(appRole -> Roles.getRolesMapping().get(appRole)).collect(Collectors.toList());
        userDto.setRoles(roles);
        return userDto;
    }
}
