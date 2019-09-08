package com.example.syfserver.dao;

import com.example.syfserver.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



@Repository
@Mapper
public interface TestDao {
    void addAll(@Param("name") String name,@Param("password") String password);
}
