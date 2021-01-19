package com.zka.lyceena.test;


import com.zka.lyceena.constants.Roles;
import com.zka.lyceena.controllers.MenusController;
import com.zka.lyceena.controllers.UserController;
import com.zka.lyceena.test.config.TestApplicationContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {TestApplicationContextConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserDetailsTest {

    private static final String BASE_REF_URL = "/users/";

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "admin_x", authorities = {Roles.ADMIN,})
    public void testUserDetails() throws Exception {
        mvc.perform(get(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }
}
