package com.zka.lyceena.services;

import com.zka.lyceena.security.UserDetails;

import java.util.List;

public interface UserDetailsProvider {

    UserDetails getCurrentUserDetails();
}
