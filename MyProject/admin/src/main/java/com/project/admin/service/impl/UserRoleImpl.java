package com.project.admin.service.impl;

import com.project.admin.dao.UserBaseInformationDao;
import com.project.admin.dao.UserRoleDao;
import com.project.admin.entity.UserBaseInformation;
import com.project.admin.entity.UserRole;
import com.project.admin.service.IUserRole;
import com.project.admin.util.BeanCopyUtil;
import com.project.admin.util.ReturnResultUtil;
import com.project.admin.vo.ReturnResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
@Slf4j
public class UserRoleImpl implements IUserRole {

    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UserBaseInformationDao userDao;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ReturnResultVo<?> addRole(UserRole dto) {
        UserRole userRole = userRoleDao.saveAndFlush(dto);
        return ReturnResultUtil.returnSuccess();
    }

    @Override
    public ReturnResultVo<?> updateRole(UserRole dto) {
        Optional<UserRole> byId = userRoleDao.findById(dto.getId());
        UserRole userRole =null;
        if (!byId.isPresent()) {
            return  ReturnResultUtil.returnFail(500,"数据不存在");
        }else{
             userRole = byId.get();
        }
        BeanCopyUtil.copyPropertiesIgnoreNull(dto,userRole);
        userRoleDao.saveAndFlush(userRole);
        return ReturnResultUtil.returnSuccess();
    }

    @Override
    public ReturnResultVo<?> bindUserAndRole(UserRole dto) {
//        UserRole userRole = userRoleDao.findById(dto.getId()).get();
//        UserBaseInformation userBaseInformation = userDao.findById(dto.getUserId()).get();
//        if(userRole==null||userBaseInformation==null){
//            return  ReturnResultUtil.returnFail(500,"数据不存在");
//        }
        UserBaseInformation userBaseInformation = entityManager.find(UserBaseInformation.class, dto.getUserId());
        UserRole userRole = entityManager.find(UserRole.class, dto.getId());
        userRole.getUserBaseInformations().add(userBaseInformation);
        userBaseInformation.getUserRoles().add(userRole);
        entityManager.persist(userRole);
        entityManager.persist(userBaseInformation);
//        userDao.saveAndFlush(userBaseInformation);
//        userRoleDao.saveAndFlush(userRole);
        return ReturnResultUtil.returnSuccess();
    }

    @Override
    public ReturnResultVo<?> findUserAndRole(UserRole dto) {
        return null;
    }
}
