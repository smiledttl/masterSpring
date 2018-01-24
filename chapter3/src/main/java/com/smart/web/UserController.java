package com.smart.web;

import com.smart.dao.UserMapper;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "cs")
    public User cs(){
        User user=userMapper.selectUserByName("admin");
        return user;
    }
}
