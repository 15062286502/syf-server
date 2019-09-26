package com.example.syfserver.controller;

import com.example.syfserver.entity.UserEntity;
import com.example.syfserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/home")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public UserEntity userLogin(@RequestBody UserEntity loginUser) {
        String name = loginUser.getName();
        String password = loginUser.getPassword();
        UserEntity user = userService.login(name);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }

    }
    @RequestMapping("/usermanage")
    public List<UserEntity> allUser(){
        List<UserEntity> user = userService.queryAllUsers();
        return user;
    }
}
