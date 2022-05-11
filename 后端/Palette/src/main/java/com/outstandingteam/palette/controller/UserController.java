package com.outstandingteam.palette.controller;

import com.outstandingteam.palette.controller.util.Result;
import com.outstandingteam.palette.controller.util.ResultCode;
import com.outstandingteam.palette.controller.util.ret.DetailLocation;
import com.outstandingteam.palette.controller.util.ret.DetailsUser;
import com.outstandingteam.palette.controller.util.ret.SimpleGoods;
import com.outstandingteam.palette.controller.util.ret.SimpleUser;
import com.outstandingteam.palette.service.ShoppingCarService;
import com.outstandingteam.palette.service.UserFollowService;
import com.outstandingteam.palette.service.UserService;
import com.outstandingteam.palette.util.PaletteLog;
import com.outstandingteam.palette.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 * 用户接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/palette/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserFollowService userFollowService;
    @Autowired
    ShoppingCarService shoppingCarService;

    /**
     * 获取其余用户的信息
     *
     * @param map
     * @return 用户的信息
     */
    @PostMapping(value = "/getOtherUser", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getOtherUser(@RequestBody Map<String, Object> map) {
        Long userId = ParamUtil.tradeToLong(map.get("userId"));        // userID

        if (userId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        SimpleUser otherUser = userService.getOtherUser(userId);
        if (otherUser == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("user", otherUser);
    }

    /**
     * 获取自己的基本信息
     *
     * @param map
     * @return 自己的基本信息
     */
    @PostMapping(value = "/getUser", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getUser(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));        // token

        if (token == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        DetailsUser user = userService.getUser(token);
        return Result.ok("user", user);
    }

    /**
     * 修改自己的基本信息
     *
     * @param map
     * @return 修改结果
     */
    @PostMapping(value = "/setUser", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result setUser(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));        // token

        String userName = (String) map.get("userName");         // 用户昵称
        String userAccount = (String) map.get("userAccount");           // 用户账号
        String userPhone = (String) map.get("userPhone");           // 手机号码
        String userPassword = (String) map.get("userPassword");         // 密码
        if (token == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        if (userName == null && userAccount == null && userPassword == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        Boolean aBoolean = userService.setUser(token, userName, userAccount, userPhone, userPassword);
        if (aBoolean == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok();
    }


    /**
     * 获取自己的邮寄保存信息
     *
     * @param map
     * @return 邮寄保存信息
     */
    @PostMapping(value = "/getHomeLocation", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getHomeLocation(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));        // token

        if (token == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        ArrayList<DetailLocation> locations = userService.getHomeLocation(token);
        if (locations == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("homeLocation", locations);
    }


    /**
     * 获取某个用户所关注用户
     *
     * @param map
     * @return 用户所关注用户
     */
    @PostMapping(value = "/getFollow", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getFollow(@RequestBody Map<String, Object> map) {
        Long userId = ParamUtil.tradeToLong(map.get("userId"));        // 用户ID

        if (userId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        ArrayList<SimpleUser> users = userFollowService.getFollow(userId);
        if (users == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("follow", users);
    }


    /**
     * 获取本人的购物车
     *
     * @param map
     * @return 购物车
     */
    @PostMapping(value = "/getShoppingCar", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getShoppingCar(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));

        Integer type = ParamUtil.tradeToInteger(map.get("type"));       // 艺术品类型 0：电子图片艺术品 1：实体艺术品 2:音乐 3：影视 4：游戏 5：模型 ，默认全部(null)
        Integer sort = ParamUtil.tradeToInteger(map.get("sort"));       // 时间排序（1：正序 2：倒序）默认为倒序(null)
        if (token == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        if (sort == null) sort = 2;

        ArrayList<SimpleGoods> arts = shoppingCarService.getShoppingCar(token, type, sort);
        if (arts == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("goods", arts);
    }


    /**
     * 将商品添加到购物车
     *
     * @param map
     * @return 购物车
     */
    @PostMapping(value = "/setShoppingCar", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result setShoppingCar(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));
        Long goodId = ParamUtil.tradeToLong(map.get("goodsId"));

        if (token == null || goodId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        Boolean isSuccess = shoppingCarService.setShoppingCar(token, goodId);
        if (isSuccess == null || !isSuccess) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok();
    }

    /**
     * 将商品从购物车中删除
     *
     * @param map
     * @return 购物车
     */
    @PostMapping(value = "/deleteShoppingCar", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result deleteShoppingCar(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));
        Long goodId = ParamUtil.tradeToLong(map.get("goodsId"));

        if (token == null || goodId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        Boolean isSuccess = shoppingCarService.deleteShoppingCar(token, goodId);
        if (isSuccess == null || !isSuccess) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok();
    }


    /**
     * 获取本人的订单，即自己的交易记录
     *
     * @param map
     * @return 交易记录
     */
    @PostMapping(value = "/getOrder", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getOrder(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));        // token

        if (token == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        Map<String, Object> temMap = userService.getOrder(token);
        if (temMap == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok(temMap);
    }


    /**
     * 获取自己的余额
     *
     * @param map
     * @return 余额
     */
    @PostMapping(value = "/getBalance", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getBalance(@RequestBody Map<String, Object> map) throws Exception {
        Long token = ParamUtil.tradeToLong(map.get("token"));        // token

        if (token == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        Double balance = userService.getBalance(token);
        if (balance == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("balance", balance);
    }


    /**
     * 设置头像
     *
     * @param token
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/setHeadImage", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result setHeadImage(Long token, @RequestParam(required = false) MultipartFile file) throws IOException {

        if (token == null || file == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        Boolean bool = userService.setHeadImage(token, file);
        if (bool == null || !bool) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok();
    }

    /**
     * 充值MTB（喵凸币）
     * @param map
     * @return
     */
    @PostMapping(value = "/chargeMTB", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result chargeMTB(@RequestBody Map<String,Object> map) throws Exception {
        Long token = ParamUtil.tradeToLong(map.get("token"));           // token
        Integer MTBAmount = ParamUtil.tradeToInteger(map.get("MTBAmount"));               // 充值金额
        String chargeEvidence = ParamUtil.tradeToString(map.get("chargeEvidence"));     // 充值凭证

        if (token == null || MTBAmount == null || chargeEvidence == null ){
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        Boolean isSuccess = userService.chargeMTB(token,MTBAmount,chargeEvidence);

        if (isSuccess == null || !isSuccess) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok();

    }


}
