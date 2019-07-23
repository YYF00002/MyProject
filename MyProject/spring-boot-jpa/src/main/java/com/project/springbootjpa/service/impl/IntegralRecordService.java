package com.project.springbootjpa.service.impl;


import com.project.springbootjpa.dao.IntegtalRecordDao;
import com.project.springbootjpa.entity.IntegralRecord;
import com.project.springbootjpa.service.IIntegralRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IntegralRecordService implements IIntegralRecord {

    @Autowired
    private IntegtalRecordDao integtalRecordDao;

    @Override
    public void addRecord(Long id) {
        IntegralRecord integralRecord = new IntegralRecord();
        integralRecord.setUid(id);
        integralRecord.setIntegral("+10");
        integralRecord.setDescription("签到加积分");
        integtalRecordDao.saveAndFlush(integralRecord);
    }
}
