package com.example.syfserver.service;

import com.example.syfserver.entity.MenuEntity;
import com.example.syfserver.entity.TestEntity;

import java.util.ArrayList;
import java.util.List;

public interface MenuService {
    List<MenuEntity> getAllMenus(String role);
    List<TestEntity> getTestList();
}
