package com.example.syfserver.service;

import com.example.syfserver.entity.UserEntity;
import org.springframework.stereotype.Service;


public interface UserService {
    UserEntity login(String name);
}
