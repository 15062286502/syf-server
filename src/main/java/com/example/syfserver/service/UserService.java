package com.example.syfserver.service;

import com.example.syfserver.entity.UserEntity;

import java.io.File;
import java.util.List;
import java.util.Map;


public interface UserService {
     void deleteSelectUser(String userName);


    UserEntity login(String name);

    int queryAllUsers(String queryName);

    List<?> queryUserPageContext(int start,int pageSize,String queryName);

    String addUser(UserEntity addUserEntity);

    void editUser(UserEntity editUserEntity);

    String uploadImage(File file,String fileName);

    Map<String ,Object> queryIndexInfo();

    Map<String,List<?>> queryMenuByRole(String role);
}
