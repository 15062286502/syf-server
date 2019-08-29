package com.example.syfserver.dao;

import com.example.syfserver.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface UserDao {
    public UserEntity userList(@Param("name")String name);
}
