package com.project.springbootjpa.service.impl;


import com.project.springbootjpa.entity.UserBaseInformation;
import com.project.springbootjpa.dao.UserBaseInformationDao;
import com.project.springbootjpa.service.IUserBaseInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserBaseInformationService implements IUserBaseInformation {

    @Autowired
    private UserBaseInformationDao userBaseInformationDao;
    @Autowired
    private IntegralRecordService integralRecordService;

    @Override
    public Long addUser() {
        UserBaseInformation ubi = new UserBaseInformation();
        ubi.setAddress("河南科技大学");
        ubi.setName("张三");
        ubi.setAge(16);
        UserBaseInformation userBaseInformation = userBaseInformationDao.saveAndFlush(ubi);
        System.out.println(userBaseInformation.toString());
        integralRecordService.addRecord(userBaseInformation.getId());
        return userBaseInformation.getId();
    }


}
