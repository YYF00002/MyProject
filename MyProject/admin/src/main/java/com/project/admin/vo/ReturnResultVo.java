package com.project.admin.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class ReturnResultVo<T> implements Serializable {


    private  Integer code;
    private String msg;
    private T data;
}
