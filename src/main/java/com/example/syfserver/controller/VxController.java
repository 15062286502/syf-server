package com.example.syfserver.controller;

import com.example.syfserver.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/vx")
public class VxController {
    @Autowired
    private TestService testService;
    @RequestMapping("/test")
    public void test(@RequestParam("username") String name,@RequestParam("password") String password){
        testService.addList(name,password);
    }
}
