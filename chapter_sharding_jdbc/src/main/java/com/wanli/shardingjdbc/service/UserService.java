package com.wanli.shardingjdbc.service;


import com.wanli.shardingjdbc.entity.User;


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
