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
            menus.add(adminMenu());
        }

        if(currentUserRoles.contains(Roles.APP_STUDENT)){
            menus.add(studentMenu());
        }
        return menus;
    }

    private static final MenuDto adminMenu(){
        MenuDto menuDto = new MenuDto();
        menuDto.setName("administration");
        menuDto.setLabel("Administration");
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

        return menuDto;
    }

    private static final MenuDto studentMenu(){
        MenuDto menuDto = new MenuDto();
        menuDto.setName("time-sheet");
        menuDto.setLabel("Emploi du temps");
        menuDto.setSubMenus(new ArrayList<>());
        return menuDto;
    }
}
