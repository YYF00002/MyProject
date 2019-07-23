package com.project.springbootjpa.dao;

import com.project.springbootjpa.entity.UserBaseInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBaseInformationDao extends JpaRepository<UserBaseInformation,Long> {
}
