package com.zka.lyceena.test;


import com.zka.lyceena.configuration.DataInit;
import com.zka.lyceena.constants.Roles;
import com.zka.lyceena.controllers.ClassesController;
import com.zka.lyceena.dao.ClassesJpaRepository;
import com.zka.lyceena.test.config.TestApplicationContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = TestApplicationContextConfig.class)
@RunWith(SpringRunner.class)
@WebMvcTest(ClassesController.class)
@Transactional
public class ClassesControllerTest {

    private static final String BASE_REF_URL = "/classes/";

    @Autowired
    private MockMvc mvc;


    @Autowired
    private ClassesJpaRepository classesJpaRepository;

    @Test
    @WithMockUser(value = "admin_x", roles = {Roles.ADMIN})
    public void findAll() throws Exception {

        mvc.perform(get(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.length()", is((int)classesJpaRepository.count())));
    }
}
