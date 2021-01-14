package com.zka.lyceena.services;

import com.zka.lyceena.dto.UserDto;
import com.zka.lyceena.security.UserDetails;

public interface UsersService {

    UserDto findCurrentUser();
}
