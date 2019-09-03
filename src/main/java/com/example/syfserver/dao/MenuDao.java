package com.example.syfserver.dao;

import com.example.syfserver.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface MenuDao {
    ArrayList<MenuEntity> menuList();
}
