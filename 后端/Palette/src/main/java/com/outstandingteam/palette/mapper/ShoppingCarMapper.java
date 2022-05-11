package com.outstandingteam.palette.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.outstandingteam.palette.controller.util.ret.SimpleGoods;
import com.outstandingteam.palette.entity.ShoppingCar;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 购物车 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface ShoppingCarMapper extends BaseMapper<ShoppingCar> {
    // 获取本人的购物车
    ArrayList<SimpleGoods> getShoppingCar(HashMap<String, Object> map);
}
