package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.controller.util.ret.SimpleGoods;
import com.outstandingteam.palette.entity.ShoppingCar;
import com.outstandingteam.palette.mapper.ShoppingCarMapper;
import com.outstandingteam.palette.service.ShoppingCarService;
import com.outstandingteam.palette.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class ShoppingCarServiceImpl extends ServiceImpl<ShoppingCarMapper, ShoppingCar> implements ShoppingCarService {

    @Autowired
    ShoppingCarMapper shoppingCarMapper;

    @Autowired
    UserService userService;

    @Override
    public ArrayList<SimpleGoods> getShoppingCar(Long token, Integer type, Integer sort) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userService.getUserIdByToken(token));
//        System.out.println(map.get("userId"));
        map.put("type", type);
        map.put("sort", sort);
        return shoppingCarMapper.getShoppingCar(map);
    }

    @Override
    public Boolean setShoppingCar(Long token, Long goodId) {
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setUserId(userService.getUserIdByToken(token));
        shoppingCar.setGoodsId(goodId);
        return shoppingCarMapper.insert(shoppingCar) > 0;
    }

    @Override
    public Boolean deleteShoppingCar(Long token, Long goodId) {
        UpdateWrapper<ShoppingCar> wrapper = new UpdateWrapper<ShoppingCar>()
                .eq("user_id", userService.getUserIdByToken(token))
                .eq("goods_id", goodId);
        return shoppingCarMapper.delete(wrapper) > 0;

    }
}
