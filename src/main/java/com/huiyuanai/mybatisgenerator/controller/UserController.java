package com.huiyuanai.mybatisgenerator.controller;

import com.huiyuanai.mybatisgenerator.entity.User;
import com.huiyuanai.mybatisgenerator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: xue rui quan
 * @create: 2020-04-15 15:46
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/selectAllUser")
    public List<User> selectAll() {
        return userService.selectAll();
    }

    @PostMapping(value = "/insertUser")
    public int insert(@RequestBody User record) {
        return userService.insert(record);
    }
}
