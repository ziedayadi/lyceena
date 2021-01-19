package com.zka.lyceena.test;


import com.zka.lyceena.controllers.MaterialReferenceController;
import com.zka.lyceena.test.config.TestApplicationContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = TestApplicationContextConfig.class)
@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationInfoTest.class)
public class ApplicationInfoTest {

    private static final String BASE_REF_URL = "/info/";

    @Autowired
    private MockMvc mvc;

    @Test
    public void testApplicationInfo() throws Exception {
        mvc.perform(get(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }
}
