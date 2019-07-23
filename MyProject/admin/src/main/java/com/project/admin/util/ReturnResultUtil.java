package com.project.admin.util;

import com.project.admin.enums.ResultEnum;
import com.project.admin.vo.ReturnResultVo;

import java.util.HashMap;
import java.util.Map;

public class ReturnResultUtil {


    public static ReturnResultVo<?> returnSuccess(Object object) {
        ReturnResultVo<Object> returnResultVo = new ReturnResultVo<Object>();
        returnResultVo.setCode(0);
        returnResultVo.setMsg("success");
        returnResultVo.setData(object);
        return returnResultVo;
    }

    public static ReturnResultVo<?> returnSuccess(String key, Object object) {
        ReturnResultVo returnResultVo = new ReturnResultVo();
        returnResultVo.setCode(0);
        returnResultVo.setMsg("success");
        Map<String, Object> map = new HashMap<>();
        map.put(key, object);
        returnResultVo.setData(map);
        return returnResultVo;
    }

    public static ReturnResultVo<?> returnSuccess() {
        return returnSuccess(null);
    }

    public static ReturnResultVo<?> returnFail(Integer code, String msg) {
        ReturnResultVo returnResultVo = new ReturnResultVo();
        returnResultVo.setCode(code);
        returnResultVo.setMsg(msg);
        return returnResultVo;
    }

    public static ReturnResultVo<?> returnFail() {
        return returnFail(ResultEnum.FAIL);
    }

    public static ReturnResultVo<?> returnFail(ResultEnum resultEnum) {
        ReturnResultVo returnResultVo = new ReturnResultVo();
        returnResultVo.setCode(resultEnum.getCode());
        returnResultVo.setMsg(resultEnum.getMessage());
        return returnResultVo;
    }

}
