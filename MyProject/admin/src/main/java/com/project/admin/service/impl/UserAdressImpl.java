package com.project.admin.service.impl;

import com.project.admin.dao.UserAdressDao;
import com.project.admin.dto.UserAdressDTO;
import com.project.admin.entity.UserAdress;
import com.project.admin.service.IUserAdress;
import com.project.admin.util.BeanCopyUtil;
import com.project.admin.util.ReturnResultUtil;
import com.project.admin.vo.ReturnResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserAdressImpl implements IUserAdress {

    @Autowired
    private UserAdressDao userAdressDao;


    @Override
    public ReturnResultVo<?> addAdress(UserAdressDTO dto) {
        List<UserAdress> byUserId = userAdressDao.findByUserId(dto.getUserId());
        boolean isDefault=true;
        if(byUserId!=null||byUserId.size()!=0){
            for (UserAdress userAdress : byUserId) {
                if(userAdress.getIsDefault()==1){
                    isDefault=false;
                }
            }
        }
        if(isDefault){
            dto.setIsDefault(1);
        }
        UserAdress userAdress = new UserAdress();
        BeanCopyUtil.copyPropertiesIgnoreNull(dto,userAdress);
        userAdressDao.saveAndFlush(userAdress);
        return ReturnResultUtil.returnSuccess();
    }
}
