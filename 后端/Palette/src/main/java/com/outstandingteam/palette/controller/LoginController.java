package com.outstandingteam.palette.controller;

import com.outstandingteam.palette.controller.util.Result;
import com.outstandingteam.palette.controller.util.ResultCode;
import com.outstandingteam.palette.service.UserService;
import com.outstandingteam.palette.util.PaletteLog;
import com.outstandingteam.palette.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 登录注册接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/palette/login")
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 通过传入账号和密码，进行登录验证
     *
     * @param map
     * @return 登录验证
     */
    @PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result login(@RequestBody Map<String, Object> map) {
        String account = ParamUtil.tradeToString(map.get("account"));       // account   账号
        String password = ParamUtil.tradeToString(map.get("password"));     // password  密码
        if (account == null || password == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        // 获取验证结果
        Long token = userService.login(account, password);
        // 如果返回的值为空，则不存在该资源
        if (token == 0l) {
            return Result.error(ResultCode.RESOURCES_NOT_FIND);
        }
        if (token == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("token", token);
    }


    /**
     * 通过传入账号和密码等个人信息，进行注册
     *
     * @param map
     * @return 验证注册
     */
    @PostMapping(value = "/registered", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result registered(@RequestBody Map<String, Object> map) throws Exception {
        String account = ParamUtil.tradeToString(map.get("account"));       // account   账号
        String password = ParamUtil.tradeToString(map.get("password"));     // password  密码
        String payKey = ParamUtil.tradeToString(map.get("payKey"));     // password  密码
        String userPhone = ParamUtil.tradeToString(map.get("userPhone"));   // userPhone 手机号码（null）
        String userName = ParamUtil.tradeToString(map.get("userName"));     // userName  昵称 （null）
        if (account == null || password == null || payKey == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        // 获取申请注册的结果
        Boolean registered = userService.registered(account, password, payKey, userPhone, userName);
        // 如果返回值为false，则插入失败，否则成功
        return registered ? Result.ok() : Result.error(ResultCode.SERVER_ERROR);
    }


    /**
     * 通过传入对于的账号，判断账号是否已经存在
     *
     * @param map
     * @return 结果
     */
    @PostMapping(value = "/accountIsBe", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result accountIsBe(@RequestBody Map<String, Object> map) {
        String account = ParamUtil.tradeToString(map.get("account"));       // account   账号
        if (account == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        Boolean accountIsBe = userService.accountIsBe(account);
        if (accountIsBe == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        // 如果返回值为false则表示该值不存在，否则存在
        return accountIsBe ? Result.ok() : Result.error(ResultCode.RESOURCES_NOT_FIND);
    }


}
