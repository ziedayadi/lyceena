package com.zka.lyceena.constants;

import java.util.HashMap;
import java.util.Map;

public class Roles {

    private Roles(){}

    public static final String ADMIN = "ADMIN";
    public static final String STUDENT = "STUDENT";
    public static final String TEACHER = "TEACHER";


    public static Map<String, String> getRolesMapping(){
        Map<String, String> rolesMapping = new HashMap<>();
        rolesMapping.put(ADMIN,ADMIN);
        rolesMapping.put(STUDENT,STUDENT);
        rolesMapping.put(TEACHER,TEACHER);
        return rolesMapping;
    }
}
