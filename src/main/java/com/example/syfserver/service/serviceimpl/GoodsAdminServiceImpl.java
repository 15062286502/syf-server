package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.GoodsAdminDao;
import com.example.syfserver.entity.DtoEntity;
import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.service.GoodsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static com.example.syfserver.constant.Resource.*;

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
    public String getGoodImgUrl(File file, String fileName) {
        InputStream is = null;
        OutputStream os = null;
        StringBuffer sb = new StringBuffer(GOODS_IMAGE_ADDRESS);
        StringBuffer url = new StringBuffer(GOODS_IMAGE_URL);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String suffix = dateFormat.format(new Date());
        try {
            sb.append(fileName + suffix + ".jpg");
            is = new FileInputStream(file);
            os = new FileOutputStream(sb.toString());
            byte[] b = new byte[is.available()];
            is.read(b, 0, b.length);
            os.write(b);
            url.append(fileName + suffix + ".jpg");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return url.toString();
    }

    @Override
    public void doAddGood(GoodsEntity goodsEntity) {
        goodsEntity.setId(UUID.randomUUID().toString().replaceAll("-",""));
        goodsAdminDao.doAddGood(goodsEntity);
    }
}
