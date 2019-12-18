package com.example.syfserver.tools;

public class RedisKeyUtil {

    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值:列名
     *
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @param column 列名
     * @return
     */
    public static String getKeyWithColumn(String majorKey,String majorKeyValue,String column){
        StringBuffer buffer = new StringBuffer();
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column);
        return buffer.toString();
    }
    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值
     *
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @return
     */
    public static String getKey(String majorKey,String majorKeyValue){
        StringBuffer buffer = new StringBuffer();
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        return buffer.toString();
    }
}
