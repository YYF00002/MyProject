package com.project.admin.service;

import com.project.admin.entity.UserRole;
import com.project.admin.vo.ReturnResultVo;

public interface IUserRole {

    ReturnResultVo<?> addRole(UserRole dto);

    ReturnResultVo<?> updateRole(UserRole dto);

    ReturnResultVo<?> bindUserAndRole(UserRole dto);

    ReturnResultVo<?> findUserAndRole(UserRole dto);

}
