package com.outstandingteam.palette.controller.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 返回数据的类型
 * @author: chenjintao
 * @Date: 2021/12/7 0:29
 */
@Data
public class Result{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回的数据")
    private Map<String, Object> data;
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "状态信息")
    private String msg;

    public Result(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public static Result ok(){
        return new Result(ResultCode.SUCCEED);
    }

    public static Result ok(String objectName,Object object){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put(objectName,object);
        return ok(stringObjectHashMap);
    }

    public static Result ok(Map<String,Object> map){
        Result ok = ok();
        ok.setData(map);
        return ok;
    }

    public static Result error(ResultCode resultCode){
        return new Result(resultCode);
    }




}
