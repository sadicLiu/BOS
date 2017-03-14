package com.liuhy.bos.dao.user.impl;

import com.liuhy.bos.dao.base.impl.BaseDaoImpl;
import com.liuhy.bos.dao.user.UserDao;
import com.liuhy.bos.model.User;
import com.liuhy.bos.utils.MD5Utils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User getLoginUser(User user) {
        String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
        List result =  this.getHibernateTemplate().find(hql, user.getUsername(), MD5Utils.md5(user.getPassword()));
        if (result.size() > 0) {
            return (User) result.get(0);
        } else {
            return null;
        }
    }
}
