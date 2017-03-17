package com.liuhy.bos.web.action;

import com.liuhy.bos.service.staff.StaffService;
import com.liuhy.bos.service.user.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>, SessionAware {

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

    /*
    * 注入所有的service
    * */
    public UserService userService;
    public StaffService staffService;

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    /*
            * 值栈操作
            * */
    public void push(Object o) {
        ActionContext.getContext().getValueStack().push(o);
    }

    public void set(String key, Object o) {
        ActionContext.getContext().getValueStack().set(key, o);
    }

    public void put(String key, Object value) {
        ActionContext.getContext().put(key, value);
    }

    public void putToSession(String key, Object value) {
        ActionContext.getContext().getSession().put(key, value);
    }


    public Map<String, Object> session;
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
