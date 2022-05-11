package com.outstandingteam.palette.controller;

import com.outstandingteam.palette.controller.util.Result;
import com.outstandingteam.palette.controller.util.ResultCode;
import com.outstandingteam.palette.controller.util.ret.SimpleArt;
import com.outstandingteam.palette.service.ArtService;
import com.outstandingteam.palette.util.Pager;
import com.outstandingteam.palette.util.PaletteLog;
import com.outstandingteam.palette.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 * 主页接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/palette/mainPage")
public class MainController {

    @Autowired
    ArtService artService;

    /**
     * 获取热度排行推荐
     *
     * @param map
     * @return 主页推荐作品
     */
    @PostMapping(value = "/getRecommendArt", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getRecommendArt(@RequestBody Map<String, Object> map) {
        Integer type = ParamUtil.tradeToInteger(map.get("type"));       // 艺术品类型 1：绘画 2:书法 3:摄影 4：音乐 5:游戏 6:模型 7:影视
        Integer num = ParamUtil.tradeToInteger(map.get("num"));     // 推荐的数量，默认3（null）
        if (type == null) type = 0;
        if (num == null) num = 3;
        ArrayList<SimpleArt> art = artService.getHotRecommendArt(type, num);
        if (art == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        System.out.println(art);
        return Result.ok("art", art);
    }

    /**
     * 获取所有的艺术品，根据条件
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/getAllArt", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getAllArt(@RequestBody Map<String, Object> map) {
        Integer type = ParamUtil.tradeToInteger(map.get("type"));       // 艺术品类型 1：绘画 2:书法 3:摄影 4：音乐 5:游戏 6:模型 7:影视
        Integer start = ParamUtil.tradeToInteger(map.get("start"));
        Integer limit = ParamUtil.tradeToInteger(map.get("limit"));
        if (type == null) type = 0;
        if (start == null) start = 0;
        if (limit == null) limit = 20;


        Pager<SimpleArt> arts = artService.getAllArt(type, start, limit);

        if (arts == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("arts", arts);
    }

    @GetMapping("/ab")
    @ResponseBody
    public Result ab(){
        return Result.ok();
    }

}
