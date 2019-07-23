package com.project.springbootmongodb.service.Impl;

import com.mongodb.client.result.UpdateResult;
import com.project.springbootmongodb.entity.User;
import com.project.springbootmongodb.service.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


@Service
public class UserImpl implements IUserDao {

    @Autowired
    private MongoTemplate mongoTemplate;




    @Override
    public void saveUser(Object user) {
        mongoTemplate.save(user);
    }

    @Override
    public void removeUser(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,User.class);
    }

    @Override
    public User findUserByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }



    @Override
    public int updateUser(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update set = new Update().set("name", user.getName()).set("age", user.getAge());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, set, User.class);
        if(updateResult!=null){
            return  (int)updateResult.getModifiedCount();
        }
        return 0;
    }

    @Override
    public User findUserAndFamuly(User user) {
        Criteria criteria = Criteria.where("id").is(user.getId()).and("familyId").is(user.getFamilyId());
        Query query = new Query(criteria);
        User one = mongoTemplate.findOne(query,User.class);

        return one;
    }
}
