package com.huiyuanai.mybatisgenerator.service;

import com.huiyuanai.mybatisgenerator.entity.User;

import java.util.List;

/**
 * @description:
 * @author: xue rui quan
 * @create: 2020-04-15 15:18
 */
public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}
