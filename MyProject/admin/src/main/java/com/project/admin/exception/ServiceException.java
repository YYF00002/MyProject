package com.project.admin.exception;


import com.project.admin.enums.ResultEnum;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	private Integer code;

    public ServiceException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
