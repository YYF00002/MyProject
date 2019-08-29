package com.project.admin.dao;


import com.project.admin.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDao extends JpaRepository<UserRole,Integer> {

}
