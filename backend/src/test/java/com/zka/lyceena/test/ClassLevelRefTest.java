package com.zka.lyceena.test;


import com.zka.lyceena.configuration.DataInit;
import com.zka.lyceena.constants.Roles;
import com.zka.lyceena.controllers.ClassLevelRefController;
import com.zka.lyceena.dao.ClassLevelRefJpaRepository;
import com.zka.lyceena.dto.ClassLevelRefDto;
import com.zka.lyceena.dto.LevelMaterialNumberOfHoursDto;
import com.zka.lyceena.dto.SaveMaterialToClassLevelDto;
import com.zka.lyceena.entities.ref.ClassLevelRef;
import com.zka.lyceena.test.config.TestApplicationContextConfig;
import com.zka.lyceena.test.util.JsonParser;
import org.junit.Before;
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

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ContextConfiguration(classes = TestApplicationContextConfig.class)
@RunWith(SpringRunner.class)
@WebMvcTest(ClassLevelRefController.class)
public class ClassLevelRefTest {

    private static final String BASE_REF_URL = "/class-level-ref/";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DataInit dataInit;

    @Autowired
    private ClassLevelRefJpaRepository levelsJpaRepository;

    @Before
    public void initData() {
        dataInit.initMaterialRef();
        dataInit.initClassRef();
        dataInit.initNumberOfHours();
    }

    @Test
    @WithMockUser(username = "admin_x", authorities = {Roles.ADMIN})
    public void testFindAll() throws Exception {
        mvc.perform(get(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin_x", authorities = {Roles.ADMIN})
    public void testSave() throws Exception {
        ClassLevelRefDto classLevelRefDto = new ClassLevelRefDto();
        classLevelRefDto.setName("TEST - TEST");
        classLevelRefDto.setId(0);

        mvc.perform(post(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(JsonParser.asJsonString(classLevelRefDto)).with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin_x", authorities = {Roles.ADMIN})
    public void testDelete() throws Exception {
        ClassLevelRef entity = new ClassLevelRef();
        entity.setId(null);
        entity.setName("TO BE DELETED");
        this.levelsJpaRepository.save(entity);

        mvc.perform(delete(BASE_REF_URL + entity.getId()).contentType(MediaType.APPLICATION_JSON_VALUE).with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin_x", authorities = {Roles.ADMIN})
    public void testFindOneById() throws Exception {
        ClassLevelRef classLevelRef = this.levelsJpaRepository.findAll().stream().findAny().orElseThrow();
        mvc.perform(get(BASE_REF_URL + classLevelRef.getId()).contentType(MediaType.APPLICATION_JSON_VALUE).with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin_x", authorities = {Roles.ADMIN})
    public void testFindMaterialsByClassLevelId() throws Exception {
        ClassLevelRef classRef = this.levelsJpaRepository.findAll().stream().findFirst().orElseThrow();
        mvc.perform(get(BASE_REF_URL + classRef.getId() + "/materials").contentType(MediaType.APPLICATION_JSON_VALUE).with(csrf())
        ).andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    @WithMockUser(username = "admin_x", authorities = {Roles.ADMIN})
    public void testDeleteClassLevelMaterialByLevelIdMaterialId() throws Exception {
        ClassLevelRef classRef = this.levelsJpaRepository.findAll().stream().findFirst().orElseThrow();
        String materialsAsJson = mvc.perform(get(BASE_REF_URL + classRef.getId() + "/materials").contentType(MediaType.APPLICATION_JSON_VALUE).with(csrf())
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        LevelMaterialNumberOfHoursDto[] levelMaterialNumberOfHoursDtos = (LevelMaterialNumberOfHoursDto[]) JsonParser.asObject(materialsAsJson, LevelMaterialNumberOfHoursDto[].class);
        String levelId = classRef.getId().toString();
        String materialId = levelMaterialNumberOfHoursDtos[0].getMaterial().getId();

        mvc.perform(delete(BASE_REF_URL + levelId + "/" + materialId).contentType(MediaType.APPLICATION_JSON_VALUE).with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin_x", authorities = {Roles.ADMIN})
    public void testUpdateOrSaveMaterialToLevel() throws Exception {

        ClassLevelRef classLevelRef = this.levelsJpaRepository.findAll().stream().findAny().orElseThrow();

        String materialsAsJson = mvc.perform(get(BASE_REF_URL + classLevelRef.getId() + "/materials").contentType(MediaType.APPLICATION_JSON_VALUE).with(csrf())
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        LevelMaterialNumberOfHoursDto[] levelMaterialNumberOfHoursDtos = (LevelMaterialNumberOfHoursDto[]) JsonParser.asObject(materialsAsJson, LevelMaterialNumberOfHoursDto[].class);

        String levelId = levelMaterialNumberOfHoursDtos[0].getClassLevelRef().getId().toString();
        String materialId = levelMaterialNumberOfHoursDtos[0].getMaterial().getId();

        SaveMaterialToClassLevelDto request = new SaveMaterialToClassLevelDto();
        request.setMaterialId(materialId);
        request.setNumberOfHours(levelMaterialNumberOfHoursDtos[0].getHourNumberPerWeek()+1);

        mvc.perform(post(BASE_REF_URL+levelId+"/save_material").contentType(MediaType.APPLICATION_JSON_VALUE).content(JsonParser.asJsonString(request)).with(csrf())
        ).andExpect(status().isOk());
    }
}
