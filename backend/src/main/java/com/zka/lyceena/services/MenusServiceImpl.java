package com.zka.lyceena.services;

import com.zka.lyceena.constants.Roles;
import com.zka.lyceena.dto.menus.MenuDto;
import com.zka.lyceena.dto.menus.SubMenuDto;
import com.zka.lyceena.security.UserDetailsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenusServiceImpl implements MenusService {

    @Autowired
    private UserDetailsProvider userDetailsProvider;

    @Override
    public List<MenuDto> getMenus() {
        List<MenuDto> menus = new ArrayList<>();
        List<String> currentUserRoles = this.userDetailsProvider.getCurrentUserDetails().getRoles();

        if(currentUserRoles.contains(Roles.APP_ADMIN)){
            menus.addAll(adminMenu());
        }

        if(currentUserRoles.contains(Roles.APP_STUDENT)){
            menus.addAll(studentMenu());
        }
        return menus;
    }

    private static final List<MenuDto> adminMenu(){
        List<MenuDto> menus = new ArrayList<>();
        MenuDto menuDto = new MenuDto();
        menuDto.setName("administration");
        menuDto.setLabel("Administration");
        menuDto.setIcon("cog");
        menuDto.setSubMenus(new ArrayList<>());

        SubMenuDto students = new SubMenuDto();
        students.setName("students");
        students.setLabel("Élèves");
        students.setRoute("students");
        menuDto.getSubMenus().add(students);

        SubMenuDto materials = new SubMenuDto();
        materials.setName("materials");
        materials.setLabel("Matières");
        materials.setRoute("materials");
        menuDto.getSubMenus().add(materials);

        SubMenuDto parents = new SubMenuDto();
        parents.setName("parents");
        parents.setLabel("Parents");
        parents.setRoute("parents");
        menuDto.getSubMenus().add(parents);

        SubMenuDto classes = new SubMenuDto();
        classes.setName("classes");
        classes.setLabel("Classes");
        classes.setRoute("classes");
        menuDto.getSubMenus().add(classes);

        SubMenuDto levels = new SubMenuDto();
        levels.setName("levels");
        levels.setLabel("Niveaux");
        levels.setRoute("class-levels-ref");
        menuDto.getSubMenus().add(levels);

        SubMenuDto employees = new SubMenuDto();
        employees.setName("employees");
        employees.setLabel("Employés");
        employees.setRoute("employees");
        menuDto.getSubMenus().add(employees);

        menus.add(menuDto);
        return menus;
    }

    private static final List<MenuDto> studentMenu(){
        List<MenuDto> menus = new ArrayList<>();
        MenuDto timeManagement = new MenuDto();
        timeManagement.setName("time-management");
        timeManagement.setLabel("Gestion du temps");
        timeManagement.setSubMenus(new ArrayList<>());
        timeManagement.setIcon("clock");

        SubMenuDto timesheet = new SubMenuDto();
        timesheet.setName("timesheet");
        timesheet.setLabel("Emploi du temps");
        timesheet.setRoute("timesheet");
        timeManagement.getSubMenus().add(timesheet);

        SubMenuDto sessions = new SubMenuDto();
        sessions.setName("sessions");
        sessions.setLabel("Sessions");
        sessions.setRoute("sessions");
        timeManagement.getSubMenus().add(sessions);



        MenuDto punition = new MenuDto();
        punition.setName("Punition");
        punition.setLabel("Punition");
        punition.setSubMenus(new ArrayList<>());
        punition.setIcon("bolt");

        SubMenuDto warnings = new SubMenuDto();
        warnings.setName("warnings");
        warnings.setLabel("Avertissements");
        warnings.setRoute("warnings");
        punition.getSubMenus().add(warnings);

        SubMenuDto renvois = new SubMenuDto();
        renvois.setName("renvois");
        renvois.setLabel("Renvois");
        renvois.setRoute("renvois");
        punition.getSubMenus().add(renvois);

        MenuDto evaluation = new MenuDto();
        evaluation.setName("evaluations");
        evaluation.setLabel("Evaluation");
        evaluation.setSubMenus(new ArrayList<>());
        evaluation.setIcon("stream");

        SubMenuDto notesByMaterial = new SubMenuDto();
        notesByMaterial.setName("noteByMat");
        notesByMaterial.setLabel("Note par matière");
        notesByMaterial.setRoute("notes-material");
        evaluation.getSubMenus().add(notesByMaterial);

        SubMenuDto noteSheet = new SubMenuDto();
        noteSheet.setName("noteSheet");
        noteSheet.setLabel("Bulletins");
        noteSheet.setRoute("note-sheet");
        evaluation.getSubMenus().add(noteSheet);

        menus.add(timeManagement);
        menus.add(punition);
        menus.add(evaluation);
        return menus;
    }
}
