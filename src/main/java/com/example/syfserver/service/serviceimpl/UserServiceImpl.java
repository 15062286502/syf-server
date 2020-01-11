package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.UserDao;
import com.example.syfserver.entity.UserEntity;
import com.example.syfserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.syfserver.tools.RemovePassword.removePassword;
import static com.example.syfserver.constant.Resource.USER_IMAGE_ADDRESS;
import static com.example.syfserver.constant.Resource.USER_IMAGE_URL;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;

    @Override
    public void deleteSelectUser(String userName) {
        userdao.excuteDelete(userName);
    }

    @Override
    public UserEntity login(String name) {
        return userdao.userList(name);
    }

    @Override
    public int queryAllUsers(String queryName) {
        if (queryName.equals("")) {
            return userdao.allUserList();
        } else if (queryName != null) {
            return userdao.queryNameCount(queryName);
        } else {
            return 0;
        }
    }

    @Override
    public List<?> queryUserPageContext(int start, int pageSize, String queryName) {
        List<UserEntity> security;
        if (queryName.equals("") || queryName == null) {
            security = userdao.userPageContext(start, pageSize);
            return removePassword(security);
        } else {
            security = userdao.queryNameResult(queryName, start, pageSize);
            return removePassword(security);
        }
    }

    @Override
    public String addUser(UserEntity addUserEntity) {
        if ((userdao.checkAddNewUser(addUserEntity)).size() != 0) {
            return "error";
        } else {
            userdao.addNewUser(addUserEntity);
            return "success";
        }
    }

    @Override
    public void editUser(UserEntity editUserEntity) {

        userdao.editUser(editUserEntity);

    }

    @Override
    public String uploadImage(File file, String fileName) {
        InputStream is = null;
        OutputStream os = null;
        StringBuffer sb = new StringBuffer(USER_IMAGE_ADDRESS);
        StringBuffer url = new StringBuffer(USER_IMAGE_URL);
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
            userdao.uploadImage(url.toString(), fileName);

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
    public Map<String, Object> queryIndexInfo() {
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("orderNum", userdao.doQueryOrderNum());
        infoMap.put("percent", userdao.doQueryPercent());
        //近五天的柱状图数据
        Map<String, Object> handleChartMap = handleChartData(userdao.doQueryInDataByDay(), userdao.doQueryOutDataByDay());
        infoMap.putAll(handleChartMap);
        return infoMap;
    }

    private Map<String, Object> handleChartData(List<Map<String, Integer>> inData, List<Map<String, Integer>> outData) {
        Map<String, Object> handleChartMap = new HashMap<>();

        List<String> chartInList = new ArrayList<>();
        List<Map<String, Object>> chartMap = new ArrayList<>();

        Map<String, Object> inMap = new HashMap<>();
        Map<String, Object> outMap = new HashMap<>();

        List<Object> inList = new ArrayList<>();
        List<Object> outList = new ArrayList<>();

        Map<String, Object> inRealte = new HashMap<>();
        Map<String, Object> outRealte = new HashMap<>();
        for (Map<String, Integer> chart :
                inData) {
            chartInList.add(String.valueOf(chart.get("chartDay")) + "号");
            inRealte.put(String.valueOf(chart.get("chartDay")) + "号", chart.get("chartNum"));
        }
        //inMap.put("data",inList);
        inMap.put("label", "店内订单");

        List<String> chartOutList = new ArrayList<>();
        for (Map<String, Integer> chart :
                outData) {
            chartOutList.add(String.valueOf(chart.get("chartDay")) + "号");
            outRealte.put(String.valueOf(chart.get("chartDay")) + "号", chart.get("chartNum"));
        }

        //outMap.put("data",outList);
        outMap.put("label", "外卖订单");

        chartInList.removeAll(chartOutList);
        chartInList.addAll(chartOutList);

        for (String day :
                chartInList) {
            if (inRealte.get(day) != null) {
                inList.add(inRealte.get(day));
            } else {
                inList.add("0");
            }

            if (outRealte.get(day) != null) {
                outList.add(outRealte.get(day));
            } else {
                outList.add("0");
            }

        }

        inMap.put("data", inList);
        outMap.put("data", outList);
        chartMap.add(inMap);
        chartMap.add(outMap);

        handleChartMap.put("dayNum", chartInList);
        handleChartMap.put("dayBottom", chartMap);
        return handleChartMap;
    }
}
