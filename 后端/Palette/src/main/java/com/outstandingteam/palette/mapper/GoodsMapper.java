package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.controller.util.ret.SimpleGoods;
import com.outstandingteam.palette.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    // 获取一个作品的现在存在的商品状态
    ArrayList<SimpleGoods> getArtGoods(Long artId);

    // 获取符合条件的一口价商品
    ArrayList<SimpleGoods> getMarkGoods(HashMap<String, Object> hashMap);

    // 通过模糊搜索对应的商品
    ArrayList<SimpleGoods> getArtFuzzy(String fuzzyStatement);
}
