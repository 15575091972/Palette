package com.outstandingteam.palette.controller;

import com.outstandingteam.palette.controller.util.Result;
import com.outstandingteam.palette.controller.util.ResultCode;
import com.outstandingteam.palette.controller.util.ret.SimpleArt;
import com.outstandingteam.palette.service.ArtService;
import com.outstandingteam.palette.util.PaletteLog;
import com.outstandingteam.palette.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 艺术品管理接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/palette/artManagement")
public class ArtManagementController {

    @Autowired
    ArtService artService;

    /**
     * 获取本人的所有作品
     *
     * @param map
     * @return 本人的所有作品
     */
    @PostMapping(value = "/getOwnerAllArt", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getOwnerAllArt(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));       // token token（null）
        Integer type = (Integer) map.get("type");       // type  艺术品类型 0：电子图片艺术品 1：实体艺术品 2:音乐 3：影视 4：游戏 5：模型 ，默认全部(null)
        Integer sort = (Integer) map.get("sort");       // sort  时间排序（1：正序 2：倒序）默认为倒序(null)
        if (token == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        if (sort == null) sort = 2;

        ArrayList<SimpleArt> arts = artService.getOwnerAllArt(token, type, sort);

        if (arts == null) {
            return Result.error(ResultCode.RESOURCES_NOT_FIND);
        }

        return Result.ok("arts", arts);
    }


    /**
     * 修改作品的基本信息
     *
     * @param map
     * @return 修改结果
     */
    @PostMapping(value = "/setArt", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result setArt(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));       // token token（null）
        Long artId = ParamUtil.tradeToLong(map.get("artId"));       // artId 艺术品ID
        String artName = (String) map.get("artName");      // 艺术品名称
        String artIntroduction = (String) map.get("artIntroduction");      // 艺术品简介
        if (token == null || artId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        Boolean isSuccess = artService.setArt(token, artId, artName, artIntroduction);
        if (isSuccess == null) {
            return Result.error(ResultCode.INSUFFICIENT_PERMISSIONS);
        }
        if (!isSuccess) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok();
    }

    /**
     * 上传一个作品
     * token  （null）
     * artName
     * artType 艺术品类型 0：电子图片艺术品 1：实体艺术品 2:音乐 3：影视 4：游戏 5：模型
     * artIntroduction
     * payKey           艺术品简介
     *
     * @return 艺术品的ID  multipart/form-data;boundary=---------------------------7d33a816d302b6
     */
    @PostMapping(value = "/uploadArt", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result uploadArt(MultipartFile file, String token, String artName,String artAuthor,
                            Integer artType, String artIntroduction, String payKey, MultipartFile[] showFile) throws IOException {

        if (token == null || Long.valueOf(token) == null || artName == null || artIntroduction == null
                || artType == null || payKey == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        Long artId = artService.uploadArt(Long.parseLong(token), artName, artIntroduction, artType, file, payKey,showFile,artAuthor);

        if (artId == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }

        return Result.ok("artId", artId);
    }


    /**
     * 转赠
     *
     * @param map
     * @return 交易记录ID
     */
    @PostMapping(value = "/givingArt", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result givingArt(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));       // token token（null）
        Long artId = ParamUtil.tradeToLong(map.get("artId"));       // artId 艺术品ID
        Double artQuantity = ParamUtil.tradeToDouble(map.get("artQuantity"));       // 转赠数量
        Long receiverId = ParamUtil.tradeToLong(map.get("receiverId"));         // 接收者

        if (token == null || artId == null || artQuantity == null || receiverId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        Boolean isSuccess = artService.givingArt(token, artId, artQuantity, receiverId);

        if (isSuccess == null) {
            return Result.error(ResultCode.RESOURCES_NOT_FIND);
        }
        if (!isSuccess) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok();
    }

    /**
     * 将艺术品挂在拍卖上
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/setAuction", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result setAuction(@RequestBody Map<String, Object> map) throws Exception {
        Long token = ParamUtil.tradeToLong(map.get("token"));       // token token
        Long artId = ParamUtil.tradeToLong(map.get("artId"));       // artId 艺术品ID
        Double artQuantity = ParamUtil.tradeToDouble(map.get("artQuantity"));       // 设置数量
        Double lowPrice = ParamUtil.tradeToDouble(map.get("lowPrice"));         // 底价
        Double minAddPrice = ParamUtil.tradeToDouble(map.get("minAddPrice"));       // 每次最小加价(null)
        Integer isRent = ParamUtil.tradeToInteger("isRent");       // 是否为出租
        Date rentStartTime = ParamUtil.tradeToDate("rentStartTime");       // 出租开始时间(null)
        Date rentEndTime = ParamUtil.tradeToDate("rentEndTime");       // 出租结束时间(null)
        Date auctionStartTime = ParamUtil.tradeToDate("auctionStartTime");     // 拍卖开始时间
        Date auctionEndTime = ParamUtil.tradeToDate("auctionEndTime");     // 拍卖结束时间
        String payKey = ParamUtil.tradeToString("payKey");     // 支付密码

        if (token == null || artId == null || artQuantity == null || lowPrice == null || isRent == null
                || auctionStartTime == null || rentEndTime == null || payKey == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        if (isRent == 1 && (rentStartTime == null || rentEndTime == null)) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        if (isRent == 1) {
            artQuantity = 1.0;
        }


        Long auctionId = artService.setAuction(token, artId, artQuantity, lowPrice,
                minAddPrice, isRent, rentStartTime, rentEndTime, auctionStartTime, auctionEndTime, payKey);

        if (auctionId == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }

        return Result.ok("auctionId", auctionId);
    }

    /**
     * 将艺术品设置为出售
     *
     * @param map
     * @return 商品ID
     */
    @PostMapping(value = "/setSell", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result setSell(@RequestBody Map<String, Object> map) throws Exception {
        Long token = ParamUtil.tradeToLong(map.get("token"));       // token token（null）
        Long artId = ParamUtil.tradeToLong(map.get("artId"));       // artId 艺术品ID
        Double artQuantity = ParamUtil.tradeToDouble(map.get("artQuantity"));       // 设置数量
        Integer isRent = ParamUtil.tradeToInteger(map.get("isRent"));       // 是否为出租
        Date rentStartTime = ParamUtil.tradeToDate(map.get("rentStartTime"));       // 出租开始时间(null)
        Date rentEndTime = ParamUtil.tradeToDate(map.get("rentEndTime"));       // 出租结束时间(null)
        Double price = ParamUtil.tradeToDouble(map.get("price"));      // 价格
        String payKey = ParamUtil.tradeToString(map.get("payKey"));     // 支付密码

        if (token == null || artId == null || artQuantity == null || isRent == null || price == null || payKey == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        if (isRent == 1 && (rentStartTime == null || rentEndTime == null)) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        if (isRent == 1) {
            artQuantity = 1.0;
        }

        Long goodsId = artService.setSell(token, artId, artQuantity, isRent, rentStartTime, rentEndTime, price, payKey);

        if (goodsId == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }

        return Result.ok("goodsId", goodsId);
    }


}
