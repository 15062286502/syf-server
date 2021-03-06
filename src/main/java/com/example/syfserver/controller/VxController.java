package com.example.syfserver.controller;

import com.example.syfserver.service.GoodsService;
import com.example.syfserver.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/vx")
public class VxController {
    @Autowired
    private TestService testService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/test")
    public void test(@RequestParam("username") String name, @RequestParam("password") String password) {
        testService.addList(name, password);
    }

    @RequestMapping("/getAllList")
    public List<?> getFoodList() {
        return goodsService.doGetAllList();
    }

    @RequestMapping("/getOrder")
    public Map<String, String> getOrderList(@RequestParam Map<String, String> good) {

        return goodsService.doGetOrder(good);
    }

    @RequestMapping("/takeInOrder")
    public List<Map<?, ?>> getTakeInOrderList(@RequestParam Map<String, String> token) {

        return goodsService.doGetRTakeInOrder(token);
    }

    @RequestMapping("/saveUser")
    public void saveUserInfo(@RequestParam Map<String, String> userInfo) {
        goodsService.doSaveVxUser(userInfo);
    }

    @RequestMapping("/saveAddress")
    public void saveVxAddress(@RequestParam Map<String, String> vxAddress) {
        goodsService.doSaveVxAddress(vxAddress);
    }

    @RequestMapping("/getAddress")
    public List<?> getVxAddress(@RequestParam Map<String, String> openId) {

        return goodsService.doSelectVxAddress(openId);
    }

    @RequestMapping("/deleteAddress")
    public void deleteVxAddress(@RequestParam Map<String, String> index) {
        goodsService.doDeleteVxAddress(index);
    }

    @RequestMapping("/takeOutOrder")
    public Map<String, String> getTakeOutOrderList(@RequestParam Map<String, String> good) {

        return goodsService.doGetTakeOutOrder(good);
    }

    @RequestMapping("/getAllTakeOutOrder")
    public List<Map<?, ?>> getAllTakeOutOrder(@RequestParam Map<String, String> token) {

        return goodsService.doGetAllTakeOutOrder(token);
    }

    @RequestMapping("/updateRemark")
    public void updateRemark(@RequestParam Map<String, Object> remarks) {
        goodsService.doUpdateRemark(remarks);
    }

}
