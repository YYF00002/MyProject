package com.project.springbootmongodb.controller;

import com.project.springbootmongodb.entity.User;
import com.project.springbootmongodb.service.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class testController {
    @Autowired
    private IUserDao userDao;

    @RequestMapping(method = RequestMethod.GET,value = "save")
    public void save() {
        User user = new User();
        user.setAge(18);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("father");
        strings.add("mother");
        user.setFamilyMember(strings);
        user.setName("张三");
        userDao.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.GET,value = "del")
    public void del() {
        User user = this.find();
        userDao.removeUser(user.getId());
    }

    @RequestMapping(method = RequestMethod.GET,value = "find")
    public User find() {

      return  userDao.findUserByName("张三");
    }

    @RequestMapping(method = RequestMethod.GET,value = "update")
    public void update() {
        User user =this.find();
        user.setAge(20);
        ArrayList<String> strings = new ArrayList<>();
        List<String> familyMember = user.getFamilyMember();
        strings.add("brother");
        strings.add("sister");
        user.setFamilyMember(strings);
        userDao.saveUser(user);
    }

}
