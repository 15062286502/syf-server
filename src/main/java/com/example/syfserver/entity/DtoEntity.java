package com.example.syfserver.entity;

public class DtoEntity {
    private String token;

    //token创建时间
    private String tokenCreatedTime;

    //失效时间
    private String tokenExpiryTime;

    private String isLogin;

    public Object getReturnObj() {
        return ReturnObj;
    }

    public void setReturnObj(Object returnObj) {
        ReturnObj = returnObj;
    }

    private Object ReturnObj;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenCreatedTime() {
        return tokenCreatedTime;
    }

    public void setTokenCreatedTime(String tokenCreatedTime) {
        this.tokenCreatedTime = tokenCreatedTime;
    }

    public String getTokenExpiryTime() {
        return tokenExpiryTime;
    }

    public void setTokenExpiryTime(String tokenExpiryTime) {
        this.tokenExpiryTime = tokenExpiryTime;
    }

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

}
