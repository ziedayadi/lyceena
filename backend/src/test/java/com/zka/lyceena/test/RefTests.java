package com.zka.lyceena.test;


import com.zka.lyceena.configuration.DataInit;
import com.zka.lyceena.controllers.RefController;
import com.zka.lyceena.test.config.TestApplicationContextConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@WebMvcTest(RefController.class)
public class RefTests {

    private static final String BASE_REF_URL = "/ref/";

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    public void findDays() throws Exception {
        mvc.perform(get(BASE_REF_URL+"days").contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk()).andExpect(content().json("[{\"id\":1,\"fr\":\"Lundi\",\"en\":\"Monday\",\"ar\":\"\"},{\"id\":2,\"fr\":\"Mardi\",\"en\":\"Tuesday\",\"ar\":\"\"},{\"id\":3,\"fr\":\"Mercredi\",\"en\":\"Wednesday\",\"ar\":\"\"},{\"id\":4,\"fr\":\"Jeudi\",\"en\":\"Thursday\",\"ar\":\"\"},{\"id\":5,\"fr\":\"Vendredi\",\"en\":\"Friday\",\"ar\":\"\"},{\"id\":6,\"fr\":\"Samedi\",\"en\":\"Satuday\",\"ar\":\"\"},{\"id\":7,\"fr\":\"Dimanche\",\"en\":\"Sunday\",\"ar\":\"\"}]"));
    }

    @Test
    @WithMockUser
    public void findHours() throws Exception {
        mvc.perform(get(BASE_REF_URL+"hours").contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk()).andExpect(content().json("[{\"id\":1,\"code\":\"08:00\"},{\"id\":2,\"code\":\"09:00\"},{\"id\":3,\"code\":\"10:00\"},{\"id\":4,\"code\":\"11:00\"},{\"id\":5,\"code\":\"12:00\"},{\"id\":6,\"code\":\"13:00\"},{\"id\":7,\"code\":\"14:00\"},{\"id\":8,\"code\":\"15:00\"},{\"id\":9,\"code\":\"16:00\"},{\"id\":10,\"code\":\"17:00\"},{\"id\":11,\"code\":\"18:00\"}]"));
    }

    @Test
    @WithMockUser
    public void findAllEmployeesTypeRef() throws Exception {
        mvc.perform(get(BASE_REF_URL+"employees-type").contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk()).andExpect(content().json("[{\"id\":1,\"name\":\"Administration\"},{\"id\":2,\"name\":\"Ménage\"},{\"id\":3,\"name\":\"Sécurité\"}]"));
    }
}
