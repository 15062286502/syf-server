package com.example.syfserver.controller;

import com.example.syfserver.entity.MenuEntity;
import com.example.syfserver.entity.TestEntity;
import com.example.syfserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/menus")
    public List<MenuEntity> showMenus(){
        List<MenuEntity> allMenus = menuService.getAllMenus();
        return allMenus;
    }
    @RequestMapping("/test")
    public List<TestEntity> test(){
        List<TestEntity> testList = menuService.getTestList();
        return testList;
    }
}
