package com.example.syfserver.service;

import java.util.List;

public interface GoodsAdminService {
    int queryAllGoods(String queryName);

    List<?> queryGoodsPageContext(int start,int pageSize,String queryName);
}
