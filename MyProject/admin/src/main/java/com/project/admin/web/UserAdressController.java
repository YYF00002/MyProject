package com.project.admin.web;


import com.project.admin.dto.UserAdressDTO;
import com.project.admin.service.IUserAdress;
import com.project.admin.vo.ReturnResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "admin/userAdress")
public class UserAdressController {

    @Autowired
    private IUserAdress iUserAdress;



    //添加地址
    @RequestMapping(path = "add-adress",method = RequestMethod.POST)
    public ReturnResultVo<?> addAdress(@RequestBody UserAdressDTO dto){

        return iUserAdress.addAdress(dto);
    }

}
