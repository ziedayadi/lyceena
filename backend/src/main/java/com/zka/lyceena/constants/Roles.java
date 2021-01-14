package com.zka.lyceena.constants;

import java.util.HashMap;
import java.util.Map;

public class Roles {
    public static final String APP_ADMIN = "app-admin";
    public static final String APP_STUDENT = "app-student";
    public static final String APP_TEACHER = "app-teacher";


    public static final String ADMIN = "ADMIN";
    public static final String STUDENT = "STUDENT";
    public static final String TEACHER = "TEACHER";


    public static Map<String, String> getRolesMapping(){
        Map<String, String> rolesMapping = new HashMap<>();
        rolesMapping.put(APP_ADMIN,ADMIN);
        rolesMapping.put(APP_STUDENT,STUDENT);
        rolesMapping.put(APP_TEACHER,TEACHER);
        return rolesMapping;
    }
}
