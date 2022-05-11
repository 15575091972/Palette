package com.outstandingteam.palette.controller.util;

import lombok.Getter;

/**
 * @Description: 返回数据的参数枚举类
 * @author: chenjintao
 * @Date: 2021/12/8 14:50
 */
@Getter
public enum ResultCode{
    //成功
    SUCCEED(200,"成功"),
    //参数不符合要求
    PARAMETER_UNQUALIFIED(401,"参数不符合要求"),
    //权限不足
    INSUFFICIENT_PERMISSIONS(402,"权限不足"),
    //没有找到资源
    RESOURCES_NOT_FIND(404,"没有找到资源"),
    //服务器内部出错
    SERVER_ERROR(500,"服务器内部出错");

    private final int code;
    private final String msg;
    ResultCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
