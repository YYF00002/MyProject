package com.project.admin.service;

import com.project.admin.dto.UserBaseInformationDTO;
import com.project.admin.vo.ReturnResultVo;

public interface IUserBaseInformation {

    ReturnResultVo<?> registeredUser(UserBaseInformationDTO user);

    ReturnResultVo<?> userLoginIn(UserBaseInformationDTO user);

    ReturnResultVo<?> findUserAdress(UserBaseInformationDTO user);

}
