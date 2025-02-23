package com.zka.lyceena.test;

import com.zka.lyceena.constants.Roles;
import com.zka.lyceena.controllers.MenusController;
import com.zka.lyceena.services.MenusService;
import com.zka.lyceena.test.config.TestApplicationContextConfig;
import com.zka.lyceena.test.util.JsonParser;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {TestApplicationContextConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(MenusController.class)
public class MenusTest {

    private static final String BASE_REF_URL = "/menus/";

    @Value("${jwt.signingkey}")
    private String signingKey;

    @Autowired
    private MenusService menusService;

    @Autowired
    private MockMvc mvc;


    @Test
    @WithMockUser(username = "admin_x", authorities = {Roles.ADMIN,})
    public void testAdminMenus() throws Exception {
        String expectedResult = JsonParser.asJsonString(this.menusService.getMenus());
        mvc.perform(get(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    @WithMockUser(username = "student_x", authorities = {Roles.STUDENT,})
    public void testStudentMenus() throws Exception {
        String expectedResult = JsonParser.asJsonString(this.menusService.getMenus());

        mvc.perform(get(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk())
                .andExpect(content().json(expectedResult));

    }

    @Test
    @WithMockUser(username = "teacher_x", authorities = {Roles.TEACHER,})
    public void testTeacherMenus() throws Exception {
        String expectedResult = JsonParser.asJsonString(this.menusService.getMenus());

        mvc.perform(get(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk())
                .andExpect(content().json(expectedResult));

    }
}
