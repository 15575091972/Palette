package com.outstandingteam.palette.service;

import com.outstandingteam.palette.controller.util.ret.SimpleGoods;
import com.outstandingteam.palette.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface GoodsService extends IService<Goods> {

    ArrayList<SimpleGoods> getArtGoods(Long artId);

    // 获取符合条件的一口价商品
    ArrayList<SimpleGoods> getMarkGoods(double topPrice, double lowPrice, Integer type);

    // 购买某个一口价的商品
    HashMap<String, Object> buyGoodsById(Long token, Long goodsId, String payKey) throws Exception;

    // 模糊查询类型的商品
    ArrayList<SimpleGoods> getArtFuzzy(String fuzzyStatement);
}
