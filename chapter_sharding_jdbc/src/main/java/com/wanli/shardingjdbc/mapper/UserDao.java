package com.wanli.shardingjdbc.mapper;


import com.wanli.shardingjdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * Title: UserDao
 * Copyright: Copyright (c) 2017
 *
 * date 2018年12月19日 14:17
 */
@Mapper
public interface UserDao {

    User getById(Long id);

    List<User>  findAll();

    void insert(User user);

    void update(User user);

}
