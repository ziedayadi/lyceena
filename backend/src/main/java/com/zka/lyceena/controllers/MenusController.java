package com.zka.lyceena.controllers;

import com.zka.lyceena.dto.menus.MenuDto;
import com.zka.lyceena.services.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/menus")
public class MenusController {

    @Autowired
    private MenusService menusService;

    @GetMapping("/")
    List<MenuDto> findMenus() {
        return this.menusService.getMenus();
    }
}
