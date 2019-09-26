package com.example.syfserver.dao;

import com.example.syfserver.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface UserDao {
    UserEntity userList(@Param("name") String name);

    List<UserEntity> allUserList();
}
