package com.example.syfserver.controller;

import com.example.syfserver.entity.DtoEntity;
import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.entity.PageResultEntity;
import com.example.syfserver.service.GoodsAdminService;
import com.example.syfserver.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.example.syfserver.tools.TransferFile.MultipartFileToFile;

@RestController
@RequestMapping("/good")
public class GoodController {
    @Autowired
    private GoodsAdminService goodsAdminService;

    @RequestMapping("/goodsManage")
    public DtoEntity getGoodsList(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam("loginName") String queryName) {
        DtoEntity dto = new DtoEntity();
        PageResultEntity pgResult = new PageResultEntity();
        pgResult.setTotal(goodsAdminService.queryAllGoods(queryName));
        pgResult.setData(goodsAdminService.queryGoodsPageContext(page * pageSize, pageSize, queryName));
        dto.setReturnObj(pgResult);
        return dto;
    }

    @RequestMapping("/goodsDelete")
    public DtoEntity userDelete(@RequestBody List<GoodsEntity> goodsList) {
        return goodsAdminService.deleteGoods(goodsList);
    }

    @RequestMapping("/uploadGoodImg")
    public String imgUpload(HttpServletRequest request, MultipartHttpServletRequest multiReq)
            throws IOException {
        String fileName = request.getParameter("name");
        MultipartFile multipartFile = multiReq.getFile("file");
        File file = MultipartFileToFile(multipartFile);
        return goodsAdminService.getGoodImgUrl(file, fileName);
    }

    @RequestMapping("/goodAdd")
    public DtoEntity goodAdd(@RequestBody GoodsEntity goodsEntity) {
        DtoEntity dtoEntity = new DtoEntity();
        List<?> goodByName = goodsAdminService.getGoodByName(goodsEntity.getName(), goodsEntity.getId());
        if (goodByName != null && !goodByName.isEmpty()) {
            dtoEntity.setIsLogin("false");
            dtoEntity.setReturnObj("商品已经存在！");
        } else {
            dtoEntity.setIsLogin("true");
            if (StringUtil.isEmpty(goodsEntity.getId())) {
                goodsAdminService.doAddGood(goodsEntity);
                dtoEntity.setReturnObj("add");
            } else {
                goodsAdminService.doUpdateGood(goodsEntity);
                dtoEntity.setReturnObj("update");
            }
        }

        return dtoEntity;
    }


    @RequestMapping("/importGoods")
    public DtoEntity importGoods(HttpServletRequest request, MultipartHttpServletRequest multiReq) {
        String fileName = request.getParameter("name");
        MultipartFile multipartFile = multiReq.getFile("file");
        return goodsAdminService.importGoods(multipartFile);
    }

}
