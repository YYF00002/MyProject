package com.project.admin.exception;

import com.project.admin.enums.ResultEnum;
import com.project.admin.util.ReturnResultUtil;
import com.project.admin.vo.ReturnResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @Description:    异常统一处理
* @Author:         YYF
* @CreateDate:     2019/7/11 17:07
* @Version:        1.0
*/
@RestControllerAdvice
@Slf4j
public class AllExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ReturnResultVo  handlerException(HttpServletRequest request, Exception e){
        log.error("【异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + e.getMessage());
        if (e instanceof ServiceException) { //业务异常 如账号密码错误
            return ReturnResultUtil.returnFail(((ServiceException) e).getCode(), e.getMessage());
        } else if (e instanceof NoHandlerFoundException) { //404接口不存在
            return ReturnResultUtil.returnFail(ResultEnum.NO_HANDLER.getCode(), ResultEnum.NO_HANDLER.getMessage());
        } else if (e instanceof ServletException) { //400接口报错
            return ReturnResultUtil.returnFail(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMessage());
        } else { //500错误
            log.error("系统内部错误", e);
            return ReturnResultUtil.returnFail(ResultEnum.SERVER_ERROR.getCode(), ResultEnum.SERVER_ERROR.getMessage());
        }
    }


    /**
     * 字段验证异常处理
     *
     * @param req 请求内容
     * @param e   异常信息
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ReturnResultVo methodArgumentNotValidExceptionErrorHandler(HttpServletRequest request, Exception e) {
        log.error("【字段验证异常拦截】" + "[" + request.getRequestURI() + "]" + "接口出现错误," + e.getMessage());
        MethodArgumentNotValidException c = (MethodArgumentNotValidException) e;
        List<FieldError> errors = c.getBindingResult().getFieldErrors();
        StringBuffer errorMsg = new StringBuffer();
        for (FieldError err : errors) {
            String message = err.getDefaultMessage();
            errorMsg.append(err.getField() + ":" + message + ",");
        }

        return ReturnResultUtil.returnFail(ResultEnum.NO_VALID_PARAM.getCode(), errorMsg.toString());
    }

}
