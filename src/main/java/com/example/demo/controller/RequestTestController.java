package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")

public class RequestTestController {
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1(String info){
        if(StringUtils.isEmpty(info)){
            return "input info.";
        }
        System.out.println(info);
        return "Content: " + info;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public List<User> test2(){
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setId(5);
        user1.setName("kkkkkkk");
        user1.setPassword("kkkk");

        User user2 = new User();
        user2.setId(2);
        user2.setName("oooo");
        user2.setPassword("ppppp");

        users.add(user1);
        users.add(user2);
        for(User u:users){
            System.out.println(u.getName());
        }
        return users;
    }
}
