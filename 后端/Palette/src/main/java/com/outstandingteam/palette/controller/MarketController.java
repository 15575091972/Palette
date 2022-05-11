package com.outstandingteam.palette.controller;

import com.outstandingteam.palette.controller.util.Result;
import com.outstandingteam.palette.controller.util.ResultCode;
import com.outstandingteam.palette.controller.util.ret.SimpleGoods;
import com.outstandingteam.palette.service.ArtService;
import com.outstandingteam.palette.service.GoodsService;
import com.outstandingteam.palette.util.PaletteLog;
import com.outstandingteam.palette.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 艺术长廊（市场）接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/palette/market")
public class MarketController {

    @Autowired
    ArtService artService;
    @Autowired
    GoodsService goodsService;

    /**
     * 筛选某类的一口价售卖的艺术品商品
     *
     * @param map
     * @return 简易艺术品集合
     */
    @PostMapping(value = "/getArts", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getArts(@RequestBody Map<String, Object> map) {
        double topPrice = Double.parseDouble(map.get("topPrice").toString());         // 限定最高价(null)
        double lowPrice = Double.parseDouble(map.get("lowPrice").toString());          // 限定最低价(null)
        Integer type = (Integer) map.get("type");               // 艺术品类型 0：电子图片艺术品 1：实体艺术品 2:音乐 3：影视 4：游戏 5：模型 ，默认全部(null)

        ArrayList<SimpleGoods> goods = goodsService.getMarkGoods(topPrice,lowPrice,type);

        if (goods == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }

        return Result.ok("goods", goods);
    }



    /**
     * 购买某个的一口价售卖(也可能是租赁)的艺术品
     *
     * @param map
     * @return 交易结果
     */
    @PostMapping(value = "/buyGoodsById", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result buyGoodsById(@RequestBody Map<String, Object> map) throws Exception {
        Long token = ParamUtil.tradeToLong(map.get("token"));        // token
        Long goodsId = ParamUtil.tradeToLong(map.get("goodsId"));        // 商品ID
        String payKey = map.get("payKey").toString();     // 支付密码

        if (token == null || goodsId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        HashMap<String, Object> returnMap = goodsService.buyGoodsById(token, goodsId,payKey);

        if (returnMap == null) {
            return Result.error(ResultCode.INSUFFICIENT_PERMISSIONS);
        }

        return Result.ok(returnMap);
    }

}
