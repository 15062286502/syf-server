package com.example.syfserver.controller;

import com.example.syfserver.entity.DtoEntity;
import com.example.syfserver.entity.PageResultEntity;
import com.example.syfserver.entity.UserEntity;
import com.example.syfserver.service.TokenService;
import com.example.syfserver.service.UserService;
import com.example.syfserver.tools.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.syfserver.tools.Encrypter.getMD5;
import static com.example.syfserver.tools.TransferFile.MultipartFileToFile;


@RestController
@RequestMapping("/home")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Resource
    private RedisService redisService;
    @Resource
    private ValueOperations<String, Object> valueOperations;

    @RequestMapping("/login")
    public DtoEntity userLogin(@RequestBody UserEntity loginUser, HttpServletRequest request) {
        String name = loginUser.getName();
        String password = getMD5(loginUser.getPassword());
        DtoEntity dto = new DtoEntity();
        UserEntity user = userService.login(name);
        if (user.getPassword() != null && user.getPassword().equals(password)) {
            user.setPassword("");

            String userAgent = request.getHeader("user-agent");
            String token = this.tokenService.generateToken(userAgent, user.getName());

            valueOperations.set(token, user.toString());
            redisService.expireKey(token, 1800, TimeUnit.SECONDS);
            String lastTime = (String) valueOperations.get(user.getName());
            Integer visit = (Integer) valueOperations.get("visitNum");
            if (visit == null) {
                visit = 0;
            }

            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime lastLoginTime = LocalDateTime.now();
            valueOperations.set(user.getName(), lastLoginTime.format(df));
            valueOperations.set("visitNum", visit + 1);

            dto.setIsLogin("true");
            dto.setToken(token);
            dto.setTokenCreatedTime(lastTime);
            dto.setTokenExpiryTime(String.valueOf(visit + 1));
            dto.setReturnObj(user);
        } else {
            dto.setIsLogin("false");
        }
        return dto;
    }

    @RequestMapping("/usermanage")
    public PageResultEntity allUser(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam("loginName") String queryName) {
        PageResultEntity pgResult = new PageResultEntity();
        pgResult.setTotal(userService.queryAllUsers(queryName));
        pgResult.setData(userService.queryUserPageContext(page * pageSize, pageSize, queryName));
        return pgResult;
    }

    @RequestMapping("/userDelete")
    public void userDelete(@RequestBody String userName) {
        userService.deleteSelectUser(userName);
    }

    @RequestMapping("/userAdd")
    public String userAdd(@RequestBody UserEntity addUserEntity) {
        return userService.addUser(addUserEntity);
    }

    @RequestMapping("/userEdit")
    public void userEdit(@RequestBody UserEntity editUserEntity) {
        userService.editUser(editUserEntity);
    }

    @RequestMapping("/userImage")
    public String uploadNewImage(@RequestParam("file") MultipartFile multipartFile, @RequestParam("userName") String name) {
        File file = MultipartFileToFile(multipartFile);
        String imageUrl = userService.uploadImage(file, name);
        return imageUrl;
    }

    @RequestMapping("/info")
    public DtoEntity getIndexInfo() {
        DtoEntity dtoEntity = new DtoEntity();
        dtoEntity.setReturnObj(userService.queryIndexInfo());
        return dtoEntity;
    }

    @RequestMapping("/getMenuByRole")
    public DtoEntity getMenuByRole(@RequestBody String role) {
        DtoEntity dtoEntity = new DtoEntity();
        dtoEntity.setReturnObj(userService.queryMenuByRole(role));
        return dtoEntity;
    }

    @RequestMapping("/setMenuByRole")
    public DtoEntity setMenuByRole(@RequestBody List<Object> menuList) {
        DtoEntity dtoEntity = new DtoEntity();
        userService.updateMenuByRole(menuList);
        dtoEntity.setIsLogin("true");
        return dtoEntity;
    }
}
