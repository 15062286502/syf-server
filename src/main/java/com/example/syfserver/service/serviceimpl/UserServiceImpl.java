package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.UserDao;
import com.example.syfserver.entity.UserEntity;
import com.example.syfserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.syfserver.tools.Resource.USER_IMAGE_ADDRESS;
import static com.example.syfserver.tools.Resource.USER_IMAGE_URL;

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
        if (queryName.equals("")) {
            return userdao.userPageContext(start, pageSize);
        } else if (queryName != null) {
            return userdao.queryNameResult(queryName, start, pageSize);
        } else {
            return null;
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
            url.append(fileName+suffix + ".jpg");
            userdao.uploadImage(url.toString(),fileName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if(os!=null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return url.toString();
    }
}
