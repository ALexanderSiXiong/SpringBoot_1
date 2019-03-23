package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    // add - delete - update - find
    List<User> findAllUsers();

    int insertUser(User User);

    int updUser(User User);

    int delUser(Integer id);

    User getUserByID(Integer id);
}
