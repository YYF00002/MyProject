package com.project.admin.dao;



import com.project.admin.entity.UserAdress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAdressDao extends JpaRepository<UserAdress,Integer> {

    List<UserAdress> findByUserId(Integer userId);
}
