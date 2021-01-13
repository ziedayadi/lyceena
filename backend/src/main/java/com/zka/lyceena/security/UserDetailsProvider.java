package com.zka.lyceena.security;

import java.util.List;

public interface UserDetailsProvider {

    List<String> getCurrentUsersRoles();
}
