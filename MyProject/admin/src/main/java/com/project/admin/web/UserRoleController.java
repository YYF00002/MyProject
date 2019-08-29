package com.project.admin.web;


import com.project.admin.entity.UserRole;
import com.project.admin.service.IUserRole;
import com.project.admin.vo.ReturnResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "admin/userRole")
public class UserRoleController {

    @Autowired
    private IUserRole iUserRole;



    //添加角色
    @RequestMapping(path = "add-role",method = RequestMethod.POST)
    public ReturnResultVo<?> addAdress(@RequestBody UserRole dto){

        return iUserRole.addRole(dto);
    }

    //修改角色
    @RequestMapping(path = "update-role",method = RequestMethod.POST)
    public ReturnResultVo<?> updateAdress(@RequestBody UserRole dto){

        return iUserRole.updateRole(dto);
    }

    //绑定用户角色关系
    @RequestMapping(path = "bind-user-and-role",method = RequestMethod.POST)
    public ReturnResultVo<?> bindUserAndRole(@RequestBody UserRole dto){

        return iUserRole.bindUserAndRole(dto);
    }


    //绑定用户角色关系
    @RequestMapping(path = "find-user-and-role",method = RequestMethod.POST)
    public ReturnResultVo<?> findUserAndRole(@RequestBody UserRole dto){

        return iUserRole.findUserAndRole(dto);
    }

}
