package com.example.syfserver.controller;

import com.example.syfserver.entity.DtoEntity;
import com.example.syfserver.entity.PageResultEntity;
import com.example.syfserver.service.GoodsAdminService;
import com.example.syfserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/good")
public class GoodController {
    @Autowired
    private GoodsAdminService goodsAdminService;
    @RequestMapping("/goodsManage")
    public DtoEntity getGoodsList(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam("loginName") String queryName){
        DtoEntity dto = new DtoEntity();
        PageResultEntity pgResult =new PageResultEntity();
        pgResult.setTotal(goodsAdminService.queryAllGoods(queryName));
        pgResult.setData(goodsAdminService.queryGoodsPageContext(page * pageSize, pageSize, queryName));
        dto.setReturnObj(pgResult);
        return dto;
    }
}
