package com.example.syfserver.service;

import com.example.syfserver.entity.UserEntity;


public interface UserService {
    UserEntity login(String name);
}
