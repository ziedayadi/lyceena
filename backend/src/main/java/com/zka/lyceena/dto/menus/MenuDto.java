package com.zka.lyceena.dto.menus;

import lombok.Data;

import java.util.List;

@Data
public class MenuDto {

    private String name;
    private String label;
    private List<SubMenuDto> subMenus;
    private String icon;

}
