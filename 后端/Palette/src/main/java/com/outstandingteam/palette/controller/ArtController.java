package com.outstandingteam.palette.controller;

import com.outstandingteam.palette.controller.util.Result;
import com.outstandingteam.palette.controller.util.ResultCode;
import com.outstandingteam.palette.controller.util.ret.*;
import com.outstandingteam.palette.service.*;
import com.outstandingteam.palette.util.PaletteLog;
import com.outstandingteam.palette.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 艺术品接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/palette/art")
public class ArtController {

    @Autowired
    ArtService artService;
    @Autowired
    ArtCommentService artCommentService;
    @Autowired
    GoodsTradeService goodsTradeService;
    @Autowired
    GoodsRentService goodsRentService;
    @Autowired
    GoodsService goodsService;


    /**
     * 进入作品的详情页面，获取其详细信息
     *
     * @param map
     * @return 作品基本信息
     */
    @PostMapping(value = "/getDetailsById", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getDetailsById(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));       // token token（null）
        Long artId = ParamUtil.tradeToLong(map.get("artId"));       // artId 艺术品ID
        if (artId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        DetailsArt detailsById = artService.getDetailsById(token, artId);
        if (detailsById == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("art", detailsById);
    }

    /**
     * 通过作品的ID获取其下评论
     *
     * @param map
     * @return 作品评论
     */
    @PostMapping(value = "/getCommentsById", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getCommentsById(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));       // token token（null）
        Long artId = ParamUtil.tradeToLong(map.get("artId"));       // artId 艺术品ID
        if (artId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        ArrayList<DetailComment> comments = artCommentService.getCommentsById(artId, token);
        if (comments == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("comments", comments);
    }

    /**
     * 发表评论
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/addComment", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result addComment(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));
        String commentContent = ParamUtil.tradeToString(map.get("commentContent"));
        Long commentReply = ParamUtil.tradeToLong(map.get("commentReply"));
        Long artId = ParamUtil.tradeToLong(map.get("artId"));
        if (token == null || commentContent == null || artId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        Boolean isSuccess = artCommentService.addComment(token, commentContent, commentReply, artId);

        if (isSuccess == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok();
    }


    /**
     * 获取一个作品的交易记录和租赁记录
     *
     * @param map
     * @return 交易记录
     */
    @PostMapping(value = "/getTradeAndRent", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getTradeAndRent(@RequestBody Map<String, Object> map) {
        Long artId = ParamUtil.tradeToLong(map.get("artId"));       // artId 艺术品ID
        if (artId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        // 获取其对应的的交易记录和租赁记录
        ArrayList<SimpleTrade> trades = goodsTradeService.getTradesByArtId(artId);
        ArrayList<SimpleRent> rents = goodsRentService.getRentsByArtId(artId);

        if (trades == null && rents == null) {
            return Result.error(ResultCode.RESOURCES_NOT_FIND);
        }

        HashMap<String, Object> temMap = new HashMap<>();
        temMap.put("trades", trades);
        temMap.put("rents", rents);
        return Result.ok(temMap);
    }

    /**
     * 获取一个作品后面同类型的推荐艺术品
     *
     * @param map
     * @return 推荐艺术品
     */
    @PostMapping(value = "/getArtRecommend", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getArtRecommend(@RequestBody Map<String, Object> map) {
        Long artId = ParamUtil.tradeToLong(map.get("artId"));       // artId 艺术品ID
        Integer num = ParamUtil.tradeToInteger(map.get("num"));// num   需要推荐的艺术品数量（默认4）(null)
        if (artId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        if (num == null) num = 4;

        // 获取一个艺术品的同类型的推荐艺术品
        ArrayList<SimpleArt> arts = artService.getArtRecommend(artId, num);

        if (arts == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("arts", arts);
    }

    /**
     * 获取一个作品的上传时间和上传者
     *
     * @param map
     * @return 作品的上传时间和上传者
     */
    @PostMapping(value = "/getUpload", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getUpload(@RequestBody Map<String, Object> map) {
        Long artId = ParamUtil.tradeToLong(map.get("artId"));       // artId 艺术品ID
        if (artId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        HashMap<String, Object> hashMap = artService.getUpload(artId);
        if (hashMap == null || hashMap.isEmpty()) {
            return Result.error(ResultCode.RESOURCES_NOT_FIND);
        }
        return Result.ok(hashMap);
    }

    /**
     * 获取一个作品的现在存在的商品状态
     *
     * @param map
     * @return 商品集合
     */
    @PostMapping(value = "/getArtGoods", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getArtGoods(@RequestBody Map<String, Object> map) {
        Long artId = ParamUtil.tradeToLong(map.get("artId"));       // artId 艺术品ID
        if (artId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        // 获取该艺术品中作为商品的存在
        ArrayList<SimpleGoods> goods = artService.getArtGoods(artId);
        if (goods == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("goods", goods);
    }

    /**
     * 通过模糊搜索对应的商品
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/getArtFuzzy", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getArtFuzzy(@RequestBody Map<String, Object> map) {
        String fuzzyStatement = ParamUtil.tradeToString(map.get("fuzzyStatement"));         // 模糊查询的语句

        if (fuzzyStatement == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        ArrayList<SimpleGoods> goods = goodsService.getArtFuzzy(fuzzyStatement);

        if (goods == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("goods", goods);
    }


}
