package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.common.ResultGenerator;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ApiController {
    @Resource
    UserDao userDao;

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<User> getOne(@PathVariable("id") Integer id){
        if(id == null || id < 1){
            return ResultGenerator.genFailResult("No Parameter");
        }
        User user = userDao.getUserByID(id);
        if(user == null){
            return ResultGenerator.genFailResult("The user no exists");
        }
        return ResultGenerator.genSuccessResult(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<User>> queryAll(){
        List<User> users = userDao.findAllUsers();
        return ResultGenerator.genSuccessResult(users);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> insert(@RequestBody User user){
        if(StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())){
            return ResultGenerator.genFailResult("Parameter Missing");
        }
        return ResultGenerator.genSuccessResult(userDao.insertUser(user) > 0);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    @ResponseBody
    public Result<Boolean> update(@RequestBody User tempUser) {
        if (tempUser.getName() == null ||
                tempUser.getId() < 1 ||
                StringUtils.isEmpty(tempUser.getName()) ||
                StringUtils.isEmpty(tempUser.getPassword())) {
            return ResultGenerator.genFailResult("Parameter Missing");
        }

        User user = userDao.getUserByID(tempUser.getId());
        if (user == null) {
            return ResultGenerator.genFailResult(" Parameter Exception");
        }

        user.setName(tempUser.getName());
        user.setPassword(tempUser.getPassword());
        return ResultGenerator.genSuccessResult(userDao.updUser(user) > 0);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Boolean> delete(@PathVariable("id") Integer id){
        if(id == null || id < 1){
            return ResultGenerator.genFailResult("Parameter Missing");
        }
        return ResultGenerator.genSuccessResult(userDao.delUser(id) > 0);
    }

}
