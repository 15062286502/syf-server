package com.example.syfserver.tools;

import com.example.syfserver.constant.OrderEnum;
import com.example.syfserver.entity.ParentOrderEntity;

public class EntityFactory {
    private static volatile EntityFactory entityFactory;

    private EntityFactory() {

    }

    public static EntityFactory getInstance() {
        if (entityFactory == null) {
            synchronized (EntityFactory.class) {
                if (entityFactory == null) {
                    entityFactory = new EntityFactory();
                }
            }
        }
        return entityFactory;
    }

    public  ParentOrderEntity getEntity(String type)  {
        String className = OrderEnum.valueOf(type).getClassName();
        ParentOrderEntity parentOrderEntity = null;
        try {
            parentOrderEntity = (ParentOrderEntity)Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return parentOrderEntity;
    }
}
