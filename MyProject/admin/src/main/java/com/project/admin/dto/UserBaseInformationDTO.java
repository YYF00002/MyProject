package com.project.admin.dto;

import com.project.admin.validate.IInsert;
import com.project.admin.validate.ISelect;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class UserBaseInformationDTO {


    private Integer id;
    private String userName;
    private Integer age;
    private String sex;
    private String headUrl;
    private String phone;
    @NotBlank(groups = {ISelect.class, IInsert.class})
    private String userCode;
    @NotBlank(groups = {ISelect.class, IInsert.class})
    private String password;

}
