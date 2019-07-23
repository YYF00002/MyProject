package com.project.springbootmongodb.service;

import com.project.springbootmongodb.entity.User;

public interface IUserDao {

     void saveUser(Object user);    //新增数据
     void removeUser(String id);    //删除数据
     User findUserByName(String name);    //通过名字查找数据
     int updateUser(User user);           //修改数据

     User findUserAndFamuly(User user);    //通过名字查找数据

}
