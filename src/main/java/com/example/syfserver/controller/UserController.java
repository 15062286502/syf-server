package com.example.syfserver.controller;

import com.example.syfserver.entity.PageResultEntity;
import com.example.syfserver.entity.UserEntity;
import com.example.syfserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.example.syfserver.tools.TransferFile.MultipartFileToFile;


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
    public PageResultEntity allUser(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam("loginName") String queryName) {
        PageResultEntity pgResult = new PageResultEntity();
        pgResult.setTotal(userService.queryAllUsers(queryName));
        pgResult.setData(userService.queryUserPageContext(page * pageSize, pageSize, queryName));
        return pgResult;
    }

    @RequestMapping("/userDelete")
    public void userDelete(@RequestBody String userName) {
        userService.deleteSelectUser(userName);
    }

    @RequestMapping("/userAdd")
    public String userAdd(@RequestBody UserEntity addUserEntity) {
        return userService.addUser(addUserEntity);
    }

    @RequestMapping("/userEdit")
    public void userEdit(@RequestBody UserEntity editUserEntity){
         userService.editUser(editUserEntity);
    }

    @RequestMapping("/userImage")
    public String uploadNewImage(@RequestParam("file") MultipartFile multipartFile,@RequestParam("userName")String name){
        File file = MultipartFileToFile(multipartFile);
        String imageUrl = userService.uploadImage(file,name);
            return imageUrl;
    }
}
