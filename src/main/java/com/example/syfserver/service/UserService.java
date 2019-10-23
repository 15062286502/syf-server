package com.example.syfserver.service;

import com.example.syfserver.entity.UserEntity;

import java.io.File;
import java.util.List;


public interface UserService {
     void deleteSelectUser(String userName);


    UserEntity login(String name);

    int queryAllUsers(String queryName);

    List<?> queryUserPageContext(int start,int pageSize,String queryName);

    String addUser(UserEntity addUserEntity);

    void editUser(UserEntity editUserEntity);

    String uploadImage(File file);
}
