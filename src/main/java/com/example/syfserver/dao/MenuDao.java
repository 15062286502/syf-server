package com.example.syfserver.dao;

import com.example.syfserver.entity.MenuEntity;
import com.example.syfserver.entity.TestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface MenuDao {
    ArrayList<MenuEntity> menuList(String role);
    ArrayList<TestEntity> getAllList();
}
