package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.GoodsAdminDao;
import com.example.syfserver.entity.DtoEntity;
import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.service.GoodsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import static com.example.syfserver.tools.RemovePassword.removePassword;

@Service
public class GoodsAdminServiceImpl implements GoodsAdminService {
    @Autowired
    private GoodsAdminDao goodsAdminDao;

    @Override
    public int queryAllGoods(String queryName) {
        if (queryName.equals("")) {
            return goodsAdminDao.allGoodsList();
        } else if (queryName != null) {
            return goodsAdminDao.queryGoodNameCount(queryName);
        } else {
            return 0;
        }
    }

    @Override
    public List<?> queryGoodsPageContext(int start, int pageSize, String queryName) {
        List<GoodsEntity> security;
        if (queryName.equals("") || queryName == null) {

            return goodsAdminDao.goodsPageContext(start, pageSize);
        } else {
            return goodsAdminDao.queryGoodNameResult(queryName, start, pageSize);
        }
    }

    @Override
    public DtoEntity deleteGoods(List<GoodsEntity> goodsList) {
        DtoEntity dto = new DtoEntity();
        Iterator<GoodsEntity> iterator = goodsList.iterator();
        while (iterator.hasNext()){
            goodsAdminDao.doDeleteGood(iterator.next().getId());
        }
        dto.setIsLogin("true");
        return dto;
    }

    @Override
    public String getGoodImgUrl(File file) {
        return null;
    }
}
