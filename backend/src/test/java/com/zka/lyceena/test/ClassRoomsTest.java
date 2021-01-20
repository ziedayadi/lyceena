package com.zka.lyceena.test;

import com.zka.lyceena.constants.Roles;
import com.zka.lyceena.controllers.ClassRoomsController;
import com.zka.lyceena.dao.ClassRoomJpaRepository;
import com.zka.lyceena.dto.ClassRoomDto;
import com.zka.lyceena.entities.rooms.ClassRoom;
import com.zka.lyceena.test.config.TestApplicationContextConfig;
import com.zka.lyceena.test.util.JsonParser;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ContextConfiguration(classes = TestApplicationContextConfig.class)
@RunWith(SpringRunner.class)
@WebMvcTest(ClassRoomsController.class)
public class ClassRoomsTest {

    private static final String BASE_REF_URL = "/class-rooms/";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ClassRoomJpaRepository classRoomJpaRepository;

    @Test
    @WithMockUser(username = "admin_x", roles = {Roles.ADMIN})
    public void findAll() throws Exception {
        mvc.perform(get(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is((int) this.classRoomJpaRepository.count())));
    }

    @Test
    @WithMockUser(username = "admin_x", roles = {Roles.ADMIN})
    public void save() throws Exception {
        ClassRoomDto classRoomDto = new ClassRoomDto();
        classRoomDto.setCapacity(100);
        classRoomDto.setName("CLASS-TEST");
        String contentAsString = JsonParser.asJsonString(classRoomDto);

        mvc.perform(post(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(contentAsString).with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin_x", roles = {Roles.ADMIN})
    public void deleteById() throws Exception {
        ClassRoom classRoom = this.classRoomJpaRepository.findAll().stream().findAny().orElseThrow();
        mvc.perform(delete(BASE_REF_URL + classRoom.getId()).contentType(MediaType.APPLICATION_JSON_VALUE).with(csrf())
        ).andExpect(status().isOk());
    }
}
