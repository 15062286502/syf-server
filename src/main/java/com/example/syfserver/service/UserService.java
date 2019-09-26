package com.example.syfserver.service;

import com.example.syfserver.entity.UserEntity;

import java.util.List;


public interface UserService {
    UserEntity login(String name);

    List<UserEntity> queryAllUsers();
}
