package com.project.admin.service;

import com.project.admin.dto.UserAdressDTO;
import com.project.admin.vo.ReturnResultVo;

public interface IUserAdress {


    ReturnResultVo<?> addAdress(UserAdressDTO dto);

}
