package com.project.admin.vo;

import lombok.Data;


@Data
public class UserBaseInformationVO {


    private Integer id;
    private String userName;
    private Integer age;
    private String sex;
    private String headUrl;
    private String phone;
    private String userCode;
    private String password;
    private String token;

}
