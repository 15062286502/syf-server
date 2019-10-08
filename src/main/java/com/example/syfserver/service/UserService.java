package com.example.syfserver.service;

import com.example.syfserver.entity.UserEntity;

import java.util.List;


public interface UserService {
    UserEntity login(String name);

    int queryAllUsers(String queryName);

    List<?> queryUserPageContext(int page,int pageSize,String queryName);
}
