package com.project.admin.dao;



import com.project.admin.entity.UserBaseInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBaseInformationDao extends JpaRepository<UserBaseInformation,Integer> {


    UserBaseInformation findByUserCode(String userCode);

    UserBaseInformation findByUserCodeAndPassword(String userCode,String password);

}
