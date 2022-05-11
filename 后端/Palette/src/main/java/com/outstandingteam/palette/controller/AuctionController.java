package com.outstandingteam.palette.controller;

import com.outstandingteam.palette.controller.util.Result;
import com.outstandingteam.palette.controller.util.ResultCode;
import com.outstandingteam.palette.controller.util.ret.DetailAuction;
import com.outstandingteam.palette.controller.util.ret.SimpleAuction;
import com.outstandingteam.palette.service.AuctionService;
import com.outstandingteam.palette.util.PaletteLog;
import com.outstandingteam.palette.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 拍卖接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/palette/auction")
public class AuctionController {

    @Autowired
    AuctionService auctionService;

    /**
     * 筛选某类的拍卖
     *
     * @param map
     * @return 拍卖场次
     */
    @PostMapping(value = "/getAuctions", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getAuctions(@RequestBody Map<String, Object> map) {

        Date auctionStartTime = ParamUtil.tradeToDate(map.get("auctionStartTime"));     // 限定开始时间
        Date auctionEndTime = ParamUtil.tradeToDate(map.get("auctionEndTime"));       // 筛选某类的拍卖（null）

        Double topPrice = ParamUtil.tradeToDouble(map.get("topPrice"));         // 限定当前价格最高价（null）
        Double lowPrice = ParamUtil.tradeToDouble(map.get("lowPrice"));         // 限定当前价格最低价（null）
        Integer type = ParamUtil.tradeToInteger(map.get("type"));           // 艺术品类型 0：电子图片艺术品 1：实体艺术品 2:音乐 3：影视 4：游戏 5：模型 ，默认全部（null）

        ArrayList<SimpleAuction> auctions = auctionService.
                getAuctions(auctionStartTime, auctionEndTime, topPrice, lowPrice, type);
        if (auctions == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("auctions", auctions);
    }

    /**
     * 搜索拍卖场次
     *
     * @param map
     * @return 拍卖场次
     */
    @PostMapping(value = "/getAuctionBySearch", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getAuctionBySearch(@RequestBody Map<String, Object> map) {
        String search = ParamUtil.tradeToString(map.get("search"));          // 搜索内容

        if (search == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        ArrayList<SimpleAuction> auctions = auctionService.getAuctionBySearch(search);
        if (auctions == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("auctions", auctions);
    }

    /**
     * 进入某个拍卖场次，获取详细下拍卖信息
     *
     * @param map
     * @return 拍卖详情
     */
    @PostMapping(value = "/getAuctionDetails", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getAuctionDetails(@RequestBody Map<String, Object> map) {
        Long auctionId = ParamUtil.tradeToLong(map.get("auctionId"));       // 拍卖号
        if (auctionId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        DetailAuction auction = auctionService.getAuctionDetails(auctionId);
        if (auction == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("auction", auction);
    }

    /**
     * 加价
     *
     * @param map
     * @return 加价记录ID
     */
    @PostMapping(value = "/addPrice", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result addPrice(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));        // token
        Long auctionId = ParamUtil.tradeToLong(map.get("auctionId"));       // 拍卖号
        Double price = ParamUtil.tradeToDouble(map.get("price"));        // 喊价
        if (token == null || auctionId == null || price == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        Long autionAddpriceId = auctionService.addPrice(token, auctionId, price);
        if (autionAddpriceId == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("autionAddpriceId", autionAddpriceId);
    }
}
