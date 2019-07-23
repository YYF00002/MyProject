package com.project.admin.web;


import com.project.admin.dto.UserBaseInformationDTO;
import com.project.admin.service.IUserBaseInformation;
import com.project.admin.validate.IInsert;
import com.project.admin.vo.ReturnResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "admin/userBaseInformation")
public class UserBaseInformationController {

    @Autowired
    private IUserBaseInformation iUserBaseInformation;

    //用户注册
    @RequestMapping(path = "registered-user",method = RequestMethod.POST)
    public ReturnResultVo<?> registeredUser(@RequestBody @Validated(IInsert.class) UserBaseInformationDTO user){

        return iUserBaseInformation.registeredUser(user);
    }

    //用户登录
    @RequestMapping(path = "user-loginIn",method = RequestMethod.POST)
    public ReturnResultVo<?> userLoginIn(@RequestBody @Validated(IInsert.class) UserBaseInformationDTO user){

        return iUserBaseInformation.userLoginIn(user);
    }

    //用户登录
    @RequestMapping(path = "find-userAdress",method = RequestMethod.POST)
    public ReturnResultVo<?> findUserAdress(@RequestBody  UserBaseInformationDTO user){

        return iUserBaseInformation.findUserAdress(user);
    }

}
