package com.project.admin.dao;


import com.project.admin.entity.RolePermissionsRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMenuInfoDao extends JpaRepository<RolePermissionsRelation,Integer> {

}
