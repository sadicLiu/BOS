package com.liuhy.bos.service.user.impl;

import com.liuhy.bos.dao.user.UserDao;
import com.liuhy.bos.model.User;
import com.liuhy.bos.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(User model) {
        return userDao.getLoginUser(model);
    }
}
