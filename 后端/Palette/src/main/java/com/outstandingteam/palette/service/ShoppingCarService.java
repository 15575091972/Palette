package com.outstandingteam.palette.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.outstandingteam.palette.controller.util.ret.SimpleGoods;
import com.outstandingteam.palette.entity.ShoppingCar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 * 购物车 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface ShoppingCarService extends IService<ShoppingCar> {
    // 获取本人的购物车
    ArrayList<SimpleGoods> getShoppingCar(Long token, Integer type, Integer sort);

    // 将某个商品添加到本人的购物车
    Boolean setShoppingCar(Long token, Long goodId);

    // 将某个商品从购物车中删除
    Boolean deleteShoppingCar(Long token, Long goodId);
}
