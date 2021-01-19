package com.zka.lyceena.test;

import com.zka.lyceena.constants.Roles;
import com.zka.lyceena.controllers.MenusController;
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
    private MockMvc mvc;


    @Test
    @WithMockUser(username = "admin_x", authorities = {Roles.ADMIN,})
    public void testAdminMenus() throws Exception {
        mvc.perform(get(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"administration\",\"label\":\"Administration\",\"subMenus\":[{\"name\":\"students\",\"label\":\"Élèves\",\"route\":\"students\"},{\"name\":\"teachers\",\"label\":\"Enseignats\",\"route\":\"teachers\"},{\"name\":\"materials\",\"label\":\"Matières\",\"route\":\"materials\"},{\"name\":\"parents\",\"label\":\"Parents\",\"route\":\"parents\"},{\"name\":\"classes\",\"label\":\"Classes\",\"route\":\"classes\"},{\"name\":\"levels\",\"label\":\"Niveaux\",\"route\":\"class-levels-ref\"},{\"name\":\"employees\",\"label\":\"Employés\",\"route\":\"employees\"}],\"icon\":\"cog\"}]"));
    }

    @Test
    @WithMockUser(username = "student_x", authorities = {Roles.STUDENT,})
    public void testStudentMenus() throws Exception {
        mvc.perform(get(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"time-management\",\"label\":\"Gestion du temps\",\"subMenus\":[{\"name\":\"timesheet\",\"label\":\"Emploi du temps\",\"route\":\"timesheet\"},{\"name\":\"sessions\",\"label\":\"Sessions\",\"route\":\"sessions\"}],\"icon\":\"clock\"},{\"name\":\"Punition\",\"label\":\"Punition\",\"subMenus\":[{\"name\":\"warnings\",\"label\":\"Avertissements\",\"route\":\"warnings\"},{\"name\":\"renvois\",\"label\":\"Renvois\",\"route\":\"renvois\"}],\"icon\":\"bolt\"},{\"name\":\"evaluations\",\"label\":\"Evaluation\",\"subMenus\":[{\"name\":\"noteByMat\",\"label\":\"Note par matière\",\"route\":\"notes-material\"},{\"name\":\"noteSheet\",\"label\":\"Bulletins\",\"route\":\"note-sheet\"}],\"icon\":\"stream\"}]"));

    }

    @Test
    @WithMockUser(username = "teacher_x", authorities = {Roles.TEACHER,})
    public void testTeacherMenus() throws Exception {
        mvc.perform(get(BASE_REF_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"time-management\",\"label\":\"Gestion du temps\",\"subMenus\":[{\"name\":\"actual-session\",\"label\":\"Session actuelle\",\"route\":\"teacher/actual-session\"},{\"name\":\"teacher/timesheet\",\"label\":\"Emploi du temps\",\"route\":\"teacher/timesheet\"},{\"name\":\"presence\",\"label\":\"Presence\",\"route\":\"teacher/presence\"}],\"icon\":\"clock\"}]"));

    }
}
