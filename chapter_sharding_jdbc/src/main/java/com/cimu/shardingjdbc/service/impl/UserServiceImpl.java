package com.cimu.shardingjdbc.service.impl;

import com.cimu.shardingjdbc.entity.User;
import com.cimu.shardingjdbc.mapper.UserDao;
import com.cimu.shardingjdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Title: UserServiceImpl
 * Copyright: Copyright (c) 2017
 *
 * date 2018年12月19日 14:17
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void save(User user) {
        userDao.insert(user);
    }

}
