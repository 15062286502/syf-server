package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.MenuDao;
import com.example.syfserver.entity.MenuEntity;
import com.example.syfserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    //获取所有菜单及其子菜单
    @Override
    public List<MenuEntity> getAllMenus() {
        List<MenuEntity> rootList = menuDao.menuList();
        List<MenuEntity> menuList = new ArrayList<>();
        for (int i = 0; i < rootList.size(); i++) {
            if (rootList.get(i).getParent_id() == 0) {
                menuList.add(rootList.get(i));
            }
        }
        for (MenuEntity menu:menuList) {
            menu.setChildMenus(getChildMenu(menu.getId(),rootList));
        }
        return menuList;
    }
    /**
     * 递归查找子菜单
     *
     * @param id
     *          当前菜单id
     * @param
     *
     * @return
     */
    private List<MenuEntity> getChildMenu(int id,List<MenuEntity>rootList){
        List <MenuEntity> childList = new ArrayList<>();
        for(MenuEntity menu : rootList){
            if(menu.getParent_id() != 0){
                if(menu.getParent_id() == id){
                    childList.add(menu);
                }
            }
        }
        return childList;
    }
}
