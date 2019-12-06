package com.cimu.shardingjdbc.service;


import com.cimu.shardingjdbc.entity.User;


/**
 * Title: UserService
 * Copyright: Copyright (c) 2017
 *
 * date 2018年12月19日 14:16
 */
public interface UserService {

    User getById(Long id);

    void update(User user);

    void save(User user);
}
