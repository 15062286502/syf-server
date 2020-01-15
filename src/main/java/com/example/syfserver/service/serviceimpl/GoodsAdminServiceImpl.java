package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.GoodsAdminDao;
import com.example.syfserver.entity.DtoEntity;
import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.service.GoodsAdminService;
import com.example.syfserver.tools.ExcelUtil;
import com.example.syfserver.tools.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
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
        while (iterator.hasNext()) {
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
        goodsEntity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        goodsAdminDao.doAddGood(goodsEntity);
    }

    @Override
    public void doUpdateGood(GoodsEntity goodsEntity) {
        goodsAdminDao.doUpdateGood(goodsEntity);
    }

    @Override
    public List<?> getGoodByName(String goodName, String goodId) {
        if (StringUtil.isEmpty(goodId)) {
            goodId = "";
        }
        return goodsAdminDao.doGetGoodByNameAndId(goodName, goodId);

    }

    @Override
    public DtoEntity importGoods(MultipartFile file) {
        DtoEntity dtoEntity =new DtoEntity();
        dtoEntity.setIsLogin("true");
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = null;

            try {
                 sheet = workbook.getSheetAt(0);
            } catch (Exception e) {
                dtoEntity.setIsLogin("false");
                dtoEntity.setReturnObj("导入的工作表必须是第一个");
                return dtoEntity;
            }
                int totalRows = sheet.getLastRowNum();
                for (int i = 1; i <= totalRows; i++) {
                    GoodsEntity goodsEntity = new GoodsEntity();
                    goodsEntity.setName(ExcelUtil.getCellValue(sheet, i, 0));
                    try {
                       String bigStr = new BigDecimal(ExcelUtil.getCellValue(sheet, i, 1)).toString();
                    } catch (Exception e) {
                        dtoEntity.setIsLogin("false");
                        dtoEntity.setReturnObj("价格必须是纯数字");
                        return dtoEntity;//异常 说明包含非数字。
                    }
                    goodsEntity.setPrice(ExcelUtil.getCellValue(sheet, i, 1));
                    goodsEntity.setGoodDesc(ExcelUtil.getCellValue(sheet, i, 2));
                    goodsEntity.setKind(ExcelUtil.getCellValue(sheet, i, 3));
                    goodsEntity.setImgUrl("");
                    List<?> goodByName = getGoodByName(goodsEntity.getName(), goodsEntity.getId());
                    if (goodByName != null && !goodByName.isEmpty()) {
                        dtoEntity.setReturnObj("存在同名商品，已跳过");
                        continue;
                    }
                    doAddGood(goodsEntity);
                }

        } catch (Exception e) {
            dtoEntity.setReturnObj("文件流错误");
            dtoEntity.setIsLogin("false");
        }
        return dtoEntity;
    }
}
