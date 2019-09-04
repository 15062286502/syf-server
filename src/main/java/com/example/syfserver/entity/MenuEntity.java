package com.example.syfserver.entity;

import java.util.List;

public class MenuEntity {
    private int id;
    private String name;
    private String url;
    private int parent_id;
    private String icon;

    public List<MenuEntity> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<MenuEntity> childMenus) {
        this.childMenus = childMenus;
    }

    private List<MenuEntity> childMenus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
