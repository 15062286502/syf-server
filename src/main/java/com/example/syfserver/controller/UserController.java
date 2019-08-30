package com.example.syfserver.controller;

import com.example.syfserver.entity.UserEntity;
import com.example.syfserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/home")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public UserEntity userLogin(@RequestParam String name,@RequestParam String password){
        UserEntity user = userService.login(name);
        if(user!=null&&user.getPassword().equals(password)){
            return user;
        }else {
            return null;
        }

    }
}
