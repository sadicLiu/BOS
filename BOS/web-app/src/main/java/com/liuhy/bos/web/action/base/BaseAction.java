package com.liuhy.bos.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    private T model;

    public T getModel() {
        return model;
    }

    public BaseAction() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type type = genericSuperclass.getActualTypeArguments()[0];
        Class<T> entityClass = (Class<T>) type;
        // 由于这是父类，在这里try一下可以避免所有子类抛异常
        try {
            model = entityClass.newInstance();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
