package com.zka.lyceena.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zka.lyceena.controllers.MaterialReferenceController;
import com.zka.lyceena.dto.MaterialDto;
import com.zka.lyceena.entities.material.Material;
import com.zka.lyceena.services.MaterialRefService;
import com.zka.lyceena.test.config.TestApplicationContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(classes = TestApplicationContextConfig.class)
@RunWith(SpringRunner.class)
@WebMvcTest(MaterialReferenceController.class)
public class MaterialRefTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MaterialRefService service;

    @Autowired
    private MaterialRefService materialRefService;


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN",})
    public void givenMaterials_whenGetMaterials_thenReturnJsonArray()
            throws Exception {

        Material material = new Material();
        material.setName("TEST MATERIAL");
        material.setDescription("TEST MATERIAL DESCRIPTION");

        List<Material> allMaterials = Collections.singletonList(material);

        given(service.findAll()).willReturn(allMaterials);

        mvc.perform(get("/ref/materials/").contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(material.getName())));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN",})
    public void saveMaterial()
            throws Exception {

        MaterialDto material = new MaterialDto();
        material.setDescription("MAT - SAVE - DESC");
        material.setName("MAT-SAVE");
        material.setId("");
        mvc.perform(post("/ref/materials/").content(asJsonString(material)).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
