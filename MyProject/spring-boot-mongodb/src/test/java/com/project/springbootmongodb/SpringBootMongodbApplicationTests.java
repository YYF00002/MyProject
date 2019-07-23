package com.project.springbootmongodb;

import com.project.springbootmongodb.entity.Family;
import com.project.springbootmongodb.entity.User;
import com.project.springbootmongodb.service.IUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMongodbApplicationTests {

    @Autowired
    private IUserDao userDao;

    @Test
    public void saveFamily() {
        Family family = new Family();
        family.setFamilyId("110");
        family.setFamilyName("家庭1");
        userDao.saveUser(family);
    }


    @Test
    public void save() {
        User user = new User();
        user.setAge(18);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("father");
        strings.add("mother");
        user.setFamilyMember(strings);
        user.setName("李四");
        user.setFamilyId("110");
        userDao.saveUser(user);
    }

    @Test
    public void del() {
        User user = userDao.findUserByName("李四");
        userDao.removeUser(user.getId());
    }






    @Test
    public void find() {
        User user = userDao.findUserByName("李四");
        User user1 = userDao.findUserAndFamuly(user);
        System.out.println(user1.toString());
    }

    @Test
    public void update() {
        User user = userDao.findUserByName("李四");
        user.setAge(20);
        List<String> familyMember = user.getFamilyMember();
        familyMember.add("brother");
        familyMember.add("sister");
        user.setFamilyMember(familyMember);
        userDao.updateUser(user);
    }

}
