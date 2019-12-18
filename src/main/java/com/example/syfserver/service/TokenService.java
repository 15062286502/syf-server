package com.example.syfserver.service;

import com.example.syfserver.entity.UserEntity;

public interface TokenService {
    String generateToken(String userAgent, String username);
    void saveToken(String token, UserEntity user);
}
