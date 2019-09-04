package com.example.syfserver.controller;

import com.example.syfserver.entity.MenuEntity;
import com.example.syfserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/menus")
    public List<MenuEntity> showMenus(){
        List<MenuEntity> allMenus = menuService.getAllMenus();
        return allMenus;
    }
}
