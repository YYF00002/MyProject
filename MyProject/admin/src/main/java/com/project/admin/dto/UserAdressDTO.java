package com.project.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
public class UserAdressDTO {

    private Integer id;

    @NotBlank
    private Integer userId;

    private String adress = "0";

    private String description;

    private String provinceCode;

    private String cityCode;

    private Integer  isDefault=0;

    private Date createDate;

    private Date lastModifiedDate;

    private Integer version;

    private Integer deleteFlag = 0;

}
