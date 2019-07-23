package com.project.springbootjpa;

import com.project.springbootjpa.service.impl.IntegralRecordService;
import com.project.springbootjpa.service.impl.UserBaseInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {

    @Autowired
    private UserBaseInformationService userBaseInformationService;

    @Autowired
    private IntegralRecordService integralRecordService;


    @Test
    @Transactional
    public void add() {
        Long aLong = userBaseInformationService.addUser();
        integralRecordService.addRecord(aLong);
    }


    @Test
    public void find() {

    }


}
