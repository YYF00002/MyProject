package com.project.admin.service.impl;

import com.project.admin.dao.UserBaseInformationDao;
import com.project.admin.dto.UserBaseInformationDTO;
import com.project.admin.entity.UserBaseInformation;
import com.project.admin.service.IUserBaseInformation;
import com.project.admin.util.BeanCopyUtil;
import com.project.admin.util.JWTUtils;
import com.project.admin.util.MD5;
import com.project.admin.util.ReturnResultUtil;
import com.project.admin.vo.ReturnResultVo;
import com.project.admin.vo.UserBaseInformationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserBaseInformationImpl implements IUserBaseInformation {

    @Autowired
    private UserBaseInformationDao userBaseInformationDao;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public ReturnResultVo<?> registeredUser(UserBaseInformationDTO user) {
            user.setPassword(MD5.encrypt16(user.getPassword()));
        UserBaseInformation byUserCodeAndDeleteFlag = userBaseInformationDao.findByUserCode(user.getUserCode());
        if(byUserCodeAndDeleteFlag!=null){
            return ReturnResultUtil.returnFail(500,"用户名已存在");
        }
        UserBaseInformation userBaseInformation = new UserBaseInformation();
        BeanCopyUtil.copyPropertiesIgnoreNull(user,userBaseInformation);
        UserBaseInformation userBaseInformation1 = userBaseInformationDao.saveAndFlush(userBaseInformation);
        System.out.println(userBaseInformation1.toString());
        return ReturnResultUtil.returnSuccess();
    }

    @Override
    public ReturnResultVo<?> userLoginIn(UserBaseInformationDTO user) {
        UserBaseInformation byUserCodeAndDeleteFlag = userBaseInformationDao.findByUserCodeAndPassword(user.getUserCode(),MD5.encrypt16(user.getPassword()));
        if(byUserCodeAndDeleteFlag==null){
            return ReturnResultUtil.returnFail(500,"该用户不存在");
        }
        byUserCodeAndDeleteFlag.setPassword(null);
        String s = jwtUtils.generateToken(byUserCodeAndDeleteFlag);
        log.info("token:{}",s);
        UserBaseInformationVO userBaseInformationVO = new UserBaseInformationVO();
        userBaseInformationVO.setToken(s);
        BeanCopyUtil.copyPropertiesIgnoreNull(byUserCodeAndDeleteFlag,userBaseInformationVO);
        return ReturnResultUtil.returnSuccess(userBaseInformationVO);
    }

    @Override
    public ReturnResultVo<?> findUserAdress(UserBaseInformationDTO user) {
        Optional<UserBaseInformation> byId = userBaseInformationDao.findById(user.getId());
        System.out.println(byId.toString());

        return ReturnResultUtil.returnSuccess(byId);
    }
}
